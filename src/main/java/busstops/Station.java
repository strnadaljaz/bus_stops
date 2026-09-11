package busstops;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class Station {
    public int id;
    public String name;

    public Station(int station_id, String stops_file) {
        this.id = station_id;

        // Source - https://stackoverflow.com/a/5868528
        // Posted by Peter Lawrey, modified by community. See post 'Timeline' for change
        // history
        // Retrieved 2026-09-10, License - CC BY-SA 4.0

        try (BufferedReader br = new BufferedReader(new FileReader(new File(stops_file)))) {
            String line = br.readLine(); // prvo vrstico izpustimo

            while ((line = br.readLine()) != null) {
                String columns[] = line.split(",");
                int id = Integer.parseInt(columns[0]);

                if (id == this.id) {
                    this.name = columns[2];
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String getName() {
        return this.name;
    }
}
