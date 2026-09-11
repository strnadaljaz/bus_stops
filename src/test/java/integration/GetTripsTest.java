package integration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import busstops.*;

class GetTripsTest {

    @Test
    void getTripsCorrectlyReadsDataFromFile() throws IOException {
        String file1 = "./src/test/java/integration/trips_test_file1.txt";
        String file2 = "./does/not/exist.txt";

        Map<String, Trip> trips = new HashMap<>();

        trips = ReadFiles.getTrips(file1);

        assertEquals(3, trips.size());

        assertThrows(IOException.class, () -> ReadFiles.getTrips(file2));
    }
}
