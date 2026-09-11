package busstops;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import static java.time.temporal.ChronoUnit.MINUTES;
import java.util.Comparator;

public class Main {
    // parametri podani od uporabnika
    static int station_id;
    static int num_buses_per_line;
    static TimeFormat time_format;

    static final String stops_file = "./gtfs/stops.txt";
    static final String stop_times_file = "./gtfs/stop_times.txt";
    static final String routes_file = "./gtfs/routes.txt";
    static final String trips_file = "./gtfs/trips.txt";

    public static void main(String args[]) throws IOException {
        if (args.length != 3) {
            System.out.println("Wrong number of arguments");
            return;
        }

        LocalTime time_now = LocalTime.now();

        station_id = Integer.parseInt(args[0]);
        num_buses_per_line = Integer.parseInt(args[1]);
        time_format = "absolute".equals(args[2]) ? TimeFormat.absolute : TimeFormat.relative;

        Station station = new Station(station_id, stops_file);

        ArrayList<StopTime> stop_times = ReadFiles.getStopTimes(station_id, stop_times_file, time_now);

        Map<Integer, String> routes_by_id = ReadFiles.getRoutes(routes_file);

        Map<String, Trip> trips_by_id = ReadFiles.getTrips(trips_file);

        Map<Integer, List<EnchantedStopTime>> by_route = new LinkedHashMap<>();

        // Povezem case s naslovi tripov in imeni routov
        for (StopTime st : stop_times) {
            Trip trip = trips_by_id.get(st.trip_id);

            String route_name = routes_by_id.get(trip.route_id);

            EnchantedStopTime e = new EnchantedStopTime(st.arrival_time, trip.headsign, trip.route_id, route_name);

            by_route.computeIfAbsent(trip.route_id, k -> new ArrayList<>()).add(e);
        }

        // Sortiram case
        for (List<EnchantedStopTime> list : by_route.values()) {
            list.sort(Comparator.comparing(e -> e.arrival_time));
        }

        // Izpis
        System.out.println(station.getName() + "\n");
        for (Map.Entry<Integer, List<EnchantedStopTime>> entry : by_route.entrySet()) {

            List<EnchantedStopTime> list = entry.getValue();

            String label = list.get(0).route_label;

            System.out.println("Route: " + label);

            int count = 0;

            for (EnchantedStopTime e : list) {
                if (count >= num_buses_per_line)
                    break;
                System.out.println(e.headsign + "\t"
                        + TimeFormat.convertTimeToString(time_format, e.arrival_time, time_now));
                ++count;
            }

            System.out.println();
        }
    }
}
