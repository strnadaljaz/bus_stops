package busstops;

public class Trip {
    public String id;
    public int route_id;
    public String headsign;

    public Trip(String id, int route_id, String headsign) {
        this.id = id;
        this.route_id = route_id;
        this.headsign = headsign;
    }
}
