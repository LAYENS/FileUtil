package app.main;

import app.service.FileService;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length ==0) {
            System.out.println("Файлы не найдены");
            return;
        }

        List<String> files = new ArrayList<>();
        String prefix = "";
        boolean isRewrite = false;
        boolean isShortStatistic = false;
        boolean isFullStatistic = false;
        boolean isSuccess;


        Path outputPath = Path.of(".");
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-o" -> outputPath = Path.of(args[++i]);
                case "-p" -> prefix = (args[++i]);
                case "-a" -> isRewrite = true;
                case "-s" -> isShortStatistic = true;
                case "-f" -> isFullStatistic = true;
                default -> files.add(args[i]);
            }
        }

        FileService service = new FileService(files, outputPath, prefix, isRewrite);

        isSuccess = service.readDataAndWrite();
        String reportText;
        if (isSuccess) {
            if (isFullStatistic) {
                reportText = "Полная статистика: ";
            } else {
                reportText = "Краткая статистика: ";
            }
            System.out.println("Данные успешно загружены в файлы");
            if (isShortStatistic || isFullStatistic) {
                System.out.println("===========================");
                System.out.println(reportText);
                service.getFullReport(isFullStatistic).forEach(((type, report) ->
                        System.out.println("=->" + type + " = " + report)));
                System.out.println("===========================");
            }
        }
    }
}
