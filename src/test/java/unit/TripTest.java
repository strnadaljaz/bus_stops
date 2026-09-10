import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TripTest {
    @Test
    void constructorAndDataAreCorrect_classTrip() {
        String trip_name = "tripname";
        int route_id = 2;
        String headsign = "headsign";

        Trip trip = new Trip(trip_name, route_id, headsign);

        assertEquals(trip_name, trip.id);
        assertEquals(route_id, trip.route_id);
        assertEquals(headsign, trip.headsign);
    }
}
