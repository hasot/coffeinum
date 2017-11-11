package com.example.torte.coffeimun2;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by torte on 11.11.2017.
 */

public class IdGenerator {

    private static final long mod = 1000000;

    public static String GetId()
    {
        Date time = Calendar.getInstance().getTime();
        long id = time.getTime() % mod;
        return "" + id;
    }

}
