package app.factory.strategy;

import app.factory.controller.Strategies;
import app.type.Types;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

public class IntegerStrategy implements Strategies {

    @Override
    public Optional<Types> getType(String data) {

        if (data == null || data.isBlank()){
            return Optional.empty();
        }
        try {
            Integer.parseInt(data);
            return Optional.of(Types.INTEGERS);
        } catch (NumberFormatException ignored) {
            return Optional.empty();
        }
    }
    @Override
    public void write(String data, Path outPath, String prefix, boolean isRewrite) {
        Path fileOutput = outPath.resolve(prefix + "integers.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileOutput.toFile(), isRewrite))){
            writer.write(data);
            writer.newLine();
        } catch (IOException ex) {
            System.out.println("Ошибка записи в файле integers.txt: " + ex.getMessage());
        }
    }
}
