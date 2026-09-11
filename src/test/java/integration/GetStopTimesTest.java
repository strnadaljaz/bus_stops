package integration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import busstops.*;

class GetStopTimesTest {

    @Test
    void getStopTimesCorrectlyReadsDataFromFile() throws IOException {
        String file1 = "./src/test/java/integration/test_files/stop_times_test_file1.txt";
        String file2 = "./does/not/exist.txt";

        assertThrows(IOException.class, () -> ReadFiles.getRoutes(file2));

        ArrayList<StopTime> stop_times = new ArrayList<>();

        stop_times = ReadFiles.getStopTimes(2, file1, LocalTime.of(21, 30));

        assertEquals(1, stop_times.size());
        StopTime stop_time = stop_times.get(0);

        assertEquals(LocalTime.of(22, 10), stop_time.arrival_time);
        assertEquals("NORMAL_03_101_Return_22:10", stop_time.trip_id);
    }
}
