package com.llc.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author lilichuan
 */
public class LocalDateTest {

    public static void main(String[] args) {
        LocalDateTime l =LocalDateTime.now();

        String s = "2016-09-14T00:00:00+08:00";
        ZonedDateTime z = ZonedDateTime.parse(s);

    }

    private static boolean isNotBefore(LocalDate date){
        return date.isAfter(LocalDate.now()) || LocalDate.now().isEqual(date);
    }
    //集合按照某个数分组，每个组抽取一个，均匀抽取
    private static void extracted() {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8);
        int need = 3;
        int interval = (list.size() / need);
        if (interval == 1) {
            System.out.println("interval:" + interval);
            list.stream().limit(need).forEach(System.out::println);
            return;
        }

        int finalInterval = interval;
        System.out.println("interval:" + finalInterval);
        list.stream().filter(storeBase -> (list.indexOf(storeBase)+1) % finalInterval == 0).forEach(System.out::println);

        System.out.println("==================");
    }

}
