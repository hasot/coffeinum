package com.example.torte.coffeimun2;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by torte on 12.11.2017.
 */

public class OrderParser {

    private final static String lineDelim = "!";
    private final static String delim = ";";
    private final static String totalString = "TOTAL";
    private final static String name = "NAME";

    private final StringBuilder builder = new StringBuilder();

    public void AddCoffeeName(String coffeName) {
        builder.append(name);
        builder.append(delim);

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

    public static ArrayList<String> Parse(String additive) {
        String[] tokens = additive.split(lineDelim);
        ArrayList<String> res = new ArrayList<>(Arrays.asList(tokens));
        return res;
    }

    public static String[] ParseItem(String token) {
        return token.split(delim);
    }
}
