package app.statistics.controller;

public interface Statistics {
    void accept(String data);
    int getCount();
    String getShortReport();
    String getFullReport();
}
