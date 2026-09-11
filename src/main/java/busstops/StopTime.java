package busstops;

import java.time.LocalTime;

public class StopTime {
    public LocalTime arrival_time;
    public String trip_id;

    public StopTime(LocalTime arrival_time, String trip_id) {
        this.arrival_time = arrival_time;
        this.trip_id = trip_id;
    }
}
