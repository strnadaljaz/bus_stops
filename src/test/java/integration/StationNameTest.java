package integration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import busstops.*;

class StationNameTest {

    @Test
    void StationConstructorGetsCorrectNameFromId() {
        int id = 2;
        String name = "AL Masjid Al-nabawi (Clock Roundabout)";
        Station station = new Station(id, "./gtfs/stops.txt");

        assertEquals(name, station.name);
    }
}
