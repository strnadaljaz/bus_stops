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

class Main {
    static int station_id;
    static int num_buses_per_line;
    static TimeFormat time_format;

    static final String stops_file = "./gtfs/stops.txt";
    static final String stop_times_file = "./gtfs/stop_times.txt";
    static final String routes_file = "./gtfs/routes.txt";
    static final String trips_file = "./gtfs/trips.txt";

    static Map<String, Trip> getTrips(String file) {
        Map<String, Trip> trips = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(new File(file)))) {
            String line = br.readLine(); // prvo vrstico izpustimo

            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");

                int route_id = Integer.parseInt(columns[0]);
                String trip_id = columns[2];
                String trip_headsign = columns[3];

                trips.put(trip_id, new Trip(trip_id, route_id, trip_headsign));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return trips;
    }

    static Map<Integer, String> getRoutes(String file) {
        Map<Integer, String> routes = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(new File(file)))) {
            String line = br.readLine(); // prvo vrstico izpustimo

            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");

                int route_id = Integer.parseInt(columns[0]);
                String route_name = columns[2];

                routes.put(route_id, route_name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return routes;
    }

    static ArrayList<StopTime> getStopTimes(final int station_id, final String file) {
        ArrayList<StopTime> stop_times = new ArrayList<>();
        LocalTime time_now = LocalTime.now();

        try (BufferedReader br = new BufferedReader(new FileReader(new File(file)))) {
            String line = br.readLine(); // prvo vrstico izpustimo

            while ((line = br.readLine()) != null) {
                String columns[] = line.split(",");

                int stop_id = Integer.parseInt(columns[3]);

                if (stop_id == station_id) {
                    String trip_id = columns[0];
                    LocalTime arrival_time = LocalTime.parse(columns[1]);

                    long diff = MINUTES.between(arrival_time, time_now);

                    if (diff <= 120)
                        stop_times.add(new StopTime(arrival_time, trip_id));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stop_times;
    }

    public static void main(String args[]) {
        if (args.length != 3) {
            System.out.println("Wrong number of arguments");
            return;
        }

        station_id = Integer.parseInt(args[0]);
        num_buses_per_line = Integer.parseInt(args[1]);
        time_format = args[2] == "absolute" ? TimeFormat.absolute : TimeFormat.relative;

        Station station = new Station(station_id, stops_file);

        ArrayList<StopTime> stop_times = getStopTimes(station_id, stop_times_file);

        Map<Integer, String> routes_by_id = getRoutes(routes_file);

        Map<String, Trip> trips_by_id = getTrips(trips_file);

        Map<Integer, List<EnchantedStopTime>> by_route = new LinkedHashMap<>();

        for (StopTime st : stop_times) {
            Trip trip = trips_by_id.get(st.trip_id);

            String route_name = routes_by_id.get(trip.route_id);

            EnchantedStopTime e = new EnchantedStopTime(st.arrival_time, trip.headsign, trip.route_id, route_name);

            by_route.computeIfAbsent(trip.route_id, k -> new ArrayList<>()).add(e);
        }

        for (List<EnchantedStopTime> list : by_route.values()) {
            list.sort(Comparator.comparing(e -> e.arrival_time));
        }

        // Izpis
        System.out.println(station.getName() + "\n");
        for (Map.Entry<Integer, List<EnchantedStopTime>> entry : by_route.entrySet()) {

            List<EnchantedStopTime> list = entry.getValue();

            String label = list.get(0).route_label;

            System.out.println(label + ":");

            for (EnchantedStopTime e : list) {
                System.out.println(e.headsign + "\t" + e.arrival_time);
            }

            System.out.println();
        }
    }
}
