package app.factory.strategy;

import app.factory.controller.Strategies;
import app.type.Types;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

public class StringStrategy implements Strategies {
    @Override
    public Optional<Types> getType(String data) {
        if (data == null || data.isBlank() || data.length() <= 1){
            return Optional.empty();
        }
        try {
            Integer.parseInt(data);
            return Optional.empty();
        } catch (NumberFormatException ignored) {}
        try {
            Double.parseDouble(data);
            return Optional.empty();
        } catch (NumberFormatException ignored) {}
        return Optional.of(Types.STRINGS);
    }
    @Override
    public void write(String data, Path outPath, String prefix, boolean isRewrite) {
        Path fileOutput = outPath.resolve(prefix + "strings.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileOutput.toFile(), isRewrite))){
            writer.write(data);
            writer.newLine();
        } catch (IOException ex) {
            System.out.println("Ошибка записи в файле strings.txt: " + ex.getMessage());
        }
    }
}
