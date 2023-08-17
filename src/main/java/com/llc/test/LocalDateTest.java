package com.llc.test;

import java.time.LocalDate;

/**
 * @author lilichuan
 */
public class LocalDateTest {

    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        LocalDate now1 = LocalDate.now();

        LocalDate tomorr = now.plusDays(1);

        System.out.println(now.isAfter(tomorr));
        System.out.println(tomorr.isAfter(now));
        System.out.println(now1.isEqual(now));

        System.out.println(isNotBefore(tomorr));

    }

    private static boolean isNotBefore(LocalDate date){
        return date.isAfter(LocalDate.now()) || LocalDate.now().isEqual(date);
    }
}
