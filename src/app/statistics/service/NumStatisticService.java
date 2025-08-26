package app.statistics.service;

import app.statistics.controller.Statistics;

public class NumStatisticService implements Statistics {
    private Double min = null;
    private Double max = null;
    private double sum = 0;
    private int count = 0;
    @Override
    public void accept(String data) {
        try {
            Double number = Double.parseDouble(data);
            count++;
            if (min == null || number < min) min = number;
            if (max == null || number > max) max = number;
            sum+=number;
        } catch (NumberFormatException ignored) {
        }
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public String getShortReport() {
        return "" + count;
    }

    @Override
    public String getFullReport() {
        if (count == 0) return "count = 0";
        double middleNumber = sum / count;
        return String.format("Количество = %d, \n Сумма чисел = %.2f, \n минимальное значение = %.2f \n " +
                "максимальное значение = %.2f \n среднее значение = %.2f", count, sum, min, max, middleNumber);
    }
}
