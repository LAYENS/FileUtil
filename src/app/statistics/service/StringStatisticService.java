package app.statistics.service;

import app.statistics.controller.Statistics;

public class StringStatisticService implements Statistics {
    private String shortWord = null;
    private String longWord = null;
    private int count = 0;

    @Override
    public void accept(String data) {
        count++;
        if (shortWord == null || data.length() < shortWord.length()) shortWord = data;
        if (longWord == null || data.length() > longWord.length()) longWord = data;
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
        return String.format("""
                 Количество = %d,\s
                 Самое короткое слово = %s,\s
                 Самое длинное слово = %s\s
                 """, count, shortWord, longWord);
    }
}
