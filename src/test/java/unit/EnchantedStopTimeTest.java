package unit;

import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import busstops.*;

class EnchantedStopTimeTest {

    @Test
    void constructorAndDataAreCorrect_classEnchantedStopTime() {
        LocalTime time = LocalTime.of(12, 30);
        String headsign = "headsing";
        int route_id = 2;
        String route_name = "routename";

        EnchantedStopTime e = new EnchantedStopTime(time, headsign, route_id, route_name);

        assertEquals(time, e.arrival_time);
        assertEquals(headsign, e.headsign);
        assertEquals(route_id, e.route_id);
        assertEquals(route_name, e.route_label);
    }
}
