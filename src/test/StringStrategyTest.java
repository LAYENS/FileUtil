package test;

import app.factory.strategy.IntegerStrategy;
import app.factory.strategy.StringStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringStrategyTest {
    private StringStrategy strategy;
    private Path tempPath;

    @BeforeEach
    public void construct() throws IOException {
        strategy = new StringStrategy();
        tempPath = Files.createTempDirectory("test-strings");
    }
    @Test
    public void testGetTypeValidData () {
        System.out.println(strategy.getType("abc").isPresent());
        assertTrue(strategy.getType("abc").isPresent());
    }
    @Test
    public void testGetTypeInvalidData () {
        assertTrue(strategy.getType("132").isEmpty());
        assertTrue(strategy.getType("13.3").isEmpty());
        assertTrue(strategy.getType("c").isEmpty());
    }
    @Test
    public void testReadAndWriteDouble() throws IOException{
        String line = "abc";
        strategy.write(line, tempPath, "prefixStrings_", true);
        Path pathAwait = tempPath.resolve("prefixStrings_strings.txt");
        assertTrue(Files.exists(pathAwait));

        String result = Files.readString(pathAwait).trim();
        assertEquals("abc", result);
    }
}
