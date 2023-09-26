package com.llc.test;


import java.net.UnknownHostException;
import java.util.List;

/**
 * @author lilichuan
 */
public class InetTest {
    public static void main(String[] args) throws UnknownHostException {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9,10,11);
        extracted(6, list);




    }

    private static void extracted(int need, List<Integer> list) {
        System.out.println("need:" + need);
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
