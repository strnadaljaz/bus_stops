import java.io.IOException;

class Main {
    static int station_id;
    static int num_buses_per_line;
    static TimeFormat time_format;

    static final String stops_file = "./gtfs/stops.txt";

    public static void main(String args[]) throws IOException {
        if (args.length != 3) {
            System.out.println("Wrong number of arguments");
            return;
        }

        station_id = Integer.parseInt(args[0]);
        num_buses_per_line = Integer.parseInt(args[1]);
        time_format = args[2] == "absolute" ? TimeFormat.absolute : TimeFormat.relative;

        Station station = new Station(station_id, stops_file);

    }
}
