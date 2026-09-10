class Main {
    static int station_id;
    static int num_buses_per_line;
    static TimeFormat time_format;

    public static void main(String args[]) {
        if (args.length != 3) {
            System.out.println("Wrong number of arguments");
            return;
        }

        station_id = Integer.parseInt(args[0]);
        num_buses_per_line = Integer.parseInt(args[1]);
        time_format = args[2] == "absolute" ? TimeFormat.absolute : TimeFormat.relative;
    }
}
