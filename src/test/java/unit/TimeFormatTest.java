import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeFormatTest {

    @Test
    void convertTimeToString_returnsCorrectString() {
        LocalTime time = LocalTime.of(12, 30);
        LocalTime now = LocalTime.of(12, 0);

        String absolute_result = TimeFormat.convertTimeToString(TimeFormat.absolute, time, now);
        String relative_result = TimeFormat.convertTimeToString(TimeFormat.relative, time, now);

        assertEquals("12:30", absolute_result);
        assertEquals("30 min", relative_result);
    }
}
