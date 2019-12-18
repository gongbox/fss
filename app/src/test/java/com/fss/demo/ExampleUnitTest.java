package com.fss.demo;


import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        long time = 1576771200000L;
        long ONE_HOUR_MILL = 1000 * 60 * 60;
        long ONE_DAY_MILL = ONE_HOUR_MILL * 24;
//        long days = TimeUnit.MILLISECONDS.toDays(time);
//        long millis = TimeUnit.DAYS.toMillis(days);
        int rawOffset = TimeZone.getDefault().getRawOffset();
//        System.out.println("days:" + days);
//        System.out.println("millis:" + millis);
        System.out.println("rawOffset:" + rawOffset);
        System.out.println("rawOffset hour:" + rawOffset / ONE_HOUR_MILL);
//        long hours = TimeUnit.MILLISECONDS.toHours(rawOffset);
//        System.out.println("hours:" + hours);
//
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        System.out.println("time format:" + sdf.format(time));
//        System.out.println("millis format:" + sdf.format(millis + rawOffset));

        long timeA = ((time + 16 * ONE_HOUR_MILL) / ONE_DAY_MILL) * ONE_DAY_MILL;
        System.out.println("millis format:" + sdf.format(new Date(timeA)));
    }

    public static String formatDate(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }
}