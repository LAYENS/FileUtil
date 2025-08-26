package app.service;
import app.factory.controller.Router;
import app.factory.core.FileStrategy;
import app.statistics.controller.Statistics;
import app.statistics.service.NumStatisticService;
import app.statistics.service.StringStatisticService;
import app.type.Types;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class FileService {
    private final Router router = new FileStrategy();
    private final Path outPath;
    private final String prefix;
    private final List<String> pathFiles;
    private final boolean isRewrite;
    private final Map<Types, Statistics> statistic = new EnumMap<>(Types.class);

    public FileService(List<String> filePaths, Path outPath, String prefix, boolean isRewrite) {
        this.pathFiles = filePaths;
        this.outPath = outPath;
        this.prefix = prefix;
        this.isRewrite = isRewrite;

        statistic.put(Types.INTEGERS, new NumStatisticService());
        statistic.put(Types.DOUBLES, new NumStatisticService());
        statistic.put(Types.STRINGS, new StringStatisticService());
    }

    public boolean readDataAndWrite() {

        for (String path : pathFiles) {
            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    String data = line;
                    if (!data.isEmpty()) {
                        router.getStrategy(data).ifPresent(strategy-> {
                            strategy.write(data, outPath, prefix, isRewrite);
                            Types type = strategy.getType(data).orElse(Types.STRINGS);
                            statistic.get(type).accept(data);
                        });


                    }
                }
            } catch (IOException ex) {
                System.out.println("Ошибка чтения файла по пути: " + path + ". Ошибка: " + ex.getMessage());
                return false;
            }
        }
        return true;
    }
    public Map<Types, String> getFullReport (boolean isFullStatistic) {
        Map<Types, String> result = new EnumMap<>(Types.class);
        statistic.forEach(((type, res) ->
                result.put(type, isFullStatistic ? res.getFullReport() : res.getShortReport())));
        return result;
    }

}
