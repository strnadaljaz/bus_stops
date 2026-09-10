package main;

import java.time.LocalTime;

public class EnchantedStopTime {
    LocalTime arrival_time;
    String headsign;
    int route_id;
    String route_label;

    EnchantedStopTime(LocalTime arrival_time, String headsign, int route_id, String route_label) {
        this.arrival_time = arrival_time;
        this.headsign = headsign;
        this.route_id = route_id;
        this.route_label = route_label;
    }
}
