import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.ChronoUnit.MINUTES;

public enum TimeFormat {
    relative,
    absolute;

    // Uporabljam ob izpisovanju glede na izbiro uporabnika
    public static String convertTimeToString(TimeFormat time_format, LocalTime time, LocalTime now) {
        if (time_format == relative) {
            int minutes = (int) MINUTES.between(now, time);
            return minutes + " min";
        } else {
            DateTimeFormatter HH_MM = DateTimeFormatter.ofPattern("HH:mm");

            return time.format(HH_MM).toString();
        }
    }
}
