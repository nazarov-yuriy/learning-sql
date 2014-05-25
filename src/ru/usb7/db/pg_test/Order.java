package ru.usb7.db.pg_test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * Created by firefish on 5/25/14.
 */
public class Order {
    BigDecimal amount;
    Date date;

    private static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

    public static Order randomOrder() {
        Order order = new Order();
        Random rnd = new Random();
        order.amount = new BigDecimal(5 + Math.abs(50 * rnd.nextGaussian()));
        GregorianCalendar gc = new GregorianCalendar();
        gc.set(gc.YEAR, randBetween(1990, 2014));

        gc.set(gc.DAY_OF_YEAR, randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR)));
        order.date = gc.getTime();
        return order;
    }
}
