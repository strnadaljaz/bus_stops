package integration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import busstops.*;

class GetRoutesTest {

    @Test
    void getRoutesCorrectlyReadsDataFromFile() throws IOException {
        String file1 = "./src/test/java/integration/routes_test_file1.txt";
        String file2 = "./does/not/exist.txt";

        Map<Integer, String> routes = new HashMap<>();

        routes = ReadFiles.getRoutes(file1);

        assertEquals(3, routes.size());

        assertEquals("101", routes.get(101));

        assertThrows(IOException.class, () -> ReadFiles.getRoutes(file2));
    }
}
