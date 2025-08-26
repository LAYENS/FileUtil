package test;

import app.factory.strategy.IntegerStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntegerStrategyTest {
   private IntegerStrategy strategy;
   private Path tempPath;

   @BeforeEach
   public void construct() throws IOException {
      strategy = new IntegerStrategy();
      tempPath = Files.createTempDirectory("test-integer");
   }

   @Test
   public void testGetTypeValidInteger() {
      System.out.println(strategy.getType("123222").isPresent());
      assertTrue(strategy.getType("123222").isPresent());
   }

   @Test
   public void testGetTypeInvalidIteger() {
      assertTrue(strategy.getType("abc").isEmpty());
   }

   @Test
   public void testReadAndWrite() throws IOException {
      String line = "123222";
      strategy.write(line, tempPath, "testPrefix_", true);
      Path pathAwait = tempPath.resolve("testPrefix_integers.txt");
      assertTrue(Files.exists(pathAwait));

      String result = Files.readString(pathAwait).trim();
      assertEquals("123222", result);
   }

}
