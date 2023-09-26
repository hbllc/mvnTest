package com.llc.test.date;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author lilichuan
 */
public class DateTest {
    public static void main(String[] args) {

        Date date = new Date();
        System.out.println(date);

        ZonedDateTime z = ZonedDateTime.now();
        System.out.println(z);

        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        System.out.println(LocalDate.now().compareTo(localDate) >= 0);
        System.out.println(!LocalDate.now().isBefore(localDate));
        System.out.println(!LocalDate.now().isBefore(LocalDate.now().plusDays(1)));
        System.out.println(!LocalDate.now().isBefore(LocalDate.now().minusDays(1)));
    }
}
