package integration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import busstops.*;

class MainProgramTest {

    @Test
    void wrongNumberOfArguments_ExitsTheProgram() throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        System.setOut(new PrintStream(output));
        try {
            Main.main(new String[] { "1", "2" });
        } finally {
            System.setOut(originalOut);
        }

        assertEquals("Wrong number of arguments\n", output.toString());
    }

    @Test
    void mainMethod_setsVariablesToCorrectParameters() throws IOException {
        Main.main(new String[] { "2", "10", "absolute" });

        assertEquals(2, Main.station_id);
        assertEquals(10, Main.num_buses_per_line);
        assertEquals(TimeFormat.absolute, Main.time_format);
    }
}
