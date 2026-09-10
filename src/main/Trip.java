package main;

public class Trip {
    String id;
    public int route_id;
    String headsign;

    Trip(String id, int route_id, String headsign) {
        this.id = id;
        this.route_id = route_id;
        this.headsign = headsign;
    }
}
