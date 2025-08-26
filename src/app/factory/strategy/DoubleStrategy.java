package app.factory.strategy;

import app.factory.controller.Strategies;
import app.type.Types;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

public class DoubleStrategy implements Strategies {
    @Override
    public Optional<Types> getType(String data) {
        try {
            Double.parseDouble(data);
            return Optional.of(Types.DOUBLES);
        } catch (NumberFormatException ignored) {
            return Optional.empty();
        }
    }
    @Override
    public void write(String data, Path outPath, String prefix, boolean isRewrite) {
        Path fileOutput = outPath.resolve(prefix + "floats.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileOutput.toFile(), isRewrite))){
            writer.write(data);
            writer.newLine();
        } catch (IOException ex) {
            System.out.println("Ошибка записи в файле floats.txt: " + ex.getMessage());
        }
    }
}
