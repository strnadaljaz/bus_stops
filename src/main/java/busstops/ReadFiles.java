package busstops;

import java.util.Map;
import java.io.BufferedReader;
import java.util.HashMap;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
}
