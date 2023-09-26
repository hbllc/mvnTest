package com.llc.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lilichuan
 */
public class Meth {

    public static void main(String[] args) {
        int i = 0;
        while (true) {
            i++;
            if (i == 3) {
                System.out.println(i);
            }
            if (i == Integer.MAX_VALUE) {
                System.out.println(i);
                i++;
                System.out.println(i);
            }
        }


//        List<Integer> needCount = List.of(2);
//
//        List<Integer> count = getList(7,7);
//
//        for (Integer integer : count) {
//            List<Integer> list = getList(1, integer);
//            for (Integer need : needCount) {
//                List<Integer> extracted = extracted(need, list);
//                if(extracted.size() != need){
//                    System.out.println("need:"+need+" size:"+extracted.size()+" count:"+integer);
//                }
//            }
//
//        }
//
//        List<Integer> list = getList(1, 7);


    }


    private static List<Integer> getList(int m, int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = m; i <= n; i++) {
            list.add(i);
        }
        return list;
    }

    private static List<Integer> extracted(int need, List<Integer> list) {

        int size = list.size();
        if (size < need) {
            return list;
        }
        int interval = (size / need);

        if ((size / interval) > need) {
            interval++;
        }
        if (size / interval < need) {
            interval--;
        }

        int finalInterval = interval;
        return list.stream().filter(storeBase -> (list.indexOf(storeBase) + 1) % finalInterval == 0).limit(need).collect(Collectors.toList());
    }


}
