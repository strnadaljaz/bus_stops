import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import static java.time.temporal.ChronoUnit.MINUTES;

class Main {
    static int station_id;
    static int num_buses_per_line;
    static TimeFormat time_format;

    static final String stops_file = "./gtfs/stops.txt";
    static final String stop_times_file = "./gtfs/stop_times.txt";

    static ArrayList<StopTime> getStopTimes(final int station_id, final String file) IOException {
        ArrayList<StopTime> stop_times = new ArrayList<>();
        LocalTime time_now = LocalTime.now();

        try (BufferedReader br = new BufferedReader(new FileReader(new File(stops_file)))) {
            String line = br.readLine(); // prvo vrstico izpustimo

            while ((line = br.readLine()) != null) {
                String columns[] = line.split(",");

                int stop_id = Integer.parseInt(columns[3]);

                if (stop_id == station_id) {
                    String trip_id = columns[0];
                    LocalTime arrival_time = LocalTime.parse(columns[1]);

                    long diff = MINUTES.between(arrival_time, time_now);

                    if (diff <= 120)
                        stop_times.add(new StopTime(arrival_time, trip_id));
                }
            }
        }
    }

    public static void main(String args[]) throws IOException {
        if (args.length != 3) {
            System.out.println("Wrong number of arguments");
            return;
        }

        station_id = Integer.parseInt(args[0]);
        num_buses_per_line = Integer.parseInt(args[1]);
        time_format = args[2] == "absolute" ? TimeFormat.absolute : TimeFormat.relative;

        Station station = new Station(station_id, stops_file);

        ArrayList<StopTime> stop_times = getStopTimes(station_id, stop_times_file);
    }
}
