package busstops;

import java.util.Map;
import java.io.BufferedReader;
import java.util.HashMap;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import static java.time.temporal.ChronoUnit.MINUTES;

public class ReadFiles {

    // Pridobi trips iz datoteke trips.txt
    // Pridobivam imena in id-je
    public static Map<String, Trip> getTrips(String file) throws IOException {
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
        }

        return trips;
    }

    // Pridobi vse case za izbrano postajo v naslednjih dveh urah
    static ArrayList<StopTime> getStopTimes(final int station_id, final String file, LocalTime time_now)
            throws IOException {
        ArrayList<StopTime> stop_times = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(new File(file)))) {
            String line = br.readLine(); // prvo vrstico izpustimo

            while ((line = br.readLine()) != null) {
                String columns[] = line.split(",");

                int stop_id = Integer.parseInt(columns[3]);

                if (stop_id == station_id) {
                    String trip_id = columns[0];
                    LocalTime arrival_time = LocalTime.parse(columns[1]);

                    long diff = MINUTES.between(time_now, arrival_time);

                    if (diff <= 120 && diff >= 0)
                        stop_times.add(new StopTime(arrival_time, trip_id));
                }
            }
        }

        return stop_times;
    }

    // Pridobi route iz routes.txt
    // Pridobivam id-je in imena
    public static Map<Integer, String> getRoutes(String file) throws IOException {
        Map<Integer, String> routes = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(new File(file)))) {
            String line = br.readLine(); // prvo vrstico izpustimo

            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");

                int route_id = Integer.parseInt(columns[0]);
                String route_name = columns[2];

                routes.put(route_id, route_name);
            }
        }

        return routes;
    }

}
