import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StopTimeTest {

    @Test
    void constructorAndDataAreCorrect_classStopTime() {
        LocalTime time = LocalTime.of(12, 30);
        String trip_name = "tripname";

        StopTime stop_time = new StopTime(time, trip_name);

        assertEquals(time, stop_time.arrival_time);
        assertEquals(trip_name, stop_time.trip_id);
    }
}
