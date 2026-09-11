package integration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import busstops.*;

class GetTripsTest {

    @Test
    void getTripsCorrectlyReadsDataFromFile() {
        String file1 = "./src/test/java/integration/trips_test_file1.txt";
        String file2 = "./src/test/java/integration/trips_test_file2.txt";

        Map<String, Trip> trips = new HashMap<>();

        trips = ReadFiles.getTrips(file1);

        assertEquals(3, trips.size());

        // trips = getTrips(file2);
    }
}
