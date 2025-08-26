package test;

import app.factory.strategy.DoubleStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DoubleStrategyTest {
    private DoubleStrategy strategy;
    private Path tempPath;

    @BeforeEach
    public void construct() throws IOException {
        strategy = new DoubleStrategy();
        tempPath = Files.createTempDirectory("test-floats");
    }

    @Test
    public void testGetTypeValidData () {
        System.out.println(strategy.getType("13.4f").isPresent());
        assertTrue(strategy.getType("13.4f").isPresent());
    }
    @Test
    public void testGetTypeInvalidData () {
        assertTrue(strategy.getType("abc").isEmpty());
    }
    @Test
    public void testReadAndWriteDouble() throws IOException{
        String line = "13.4";
        strategy.write(line, tempPath, "prefixDouble_", true);
        Path pathAwait = tempPath.resolve("prefixDouble_floats.txt");
        assertTrue(Files.exists(pathAwait));

        String result = Files.readString(pathAwait).trim();
        assertEquals("13.4", result);
    }
}
