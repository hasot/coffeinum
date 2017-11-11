package com.example.torte.coffeimun2;

/**
 * Created by torte on 12.11.2017.
 */

public class OrderParser {

    private final static char lineDelim = '!';
    private final static char delim = ';';
    private final static String totalString = "TOTAL";

    private final StringBuilder builder = new StringBuilder();

    public void AddCoffeeName(String coffeName) {
        builder.append(coffeName);
        builder.append(lineDelim);
    }

    public void AddAdditive(String add, int count, int cost) {
        builder.append(add);
        builder.append(delim);

        builder.append(count);
        builder.append(delim);

        builder.append(cost);
        builder.append(lineDelim);
    }

    public void AddTotal(int totalCost) {
        builder.append(totalString);
        builder.append(delim);

        builder.append(totalCost);
        builder.append(lineDelim);
    }

    public String Print() {
        return builder.toString();
    }
}
