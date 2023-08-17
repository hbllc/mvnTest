package com.llc.test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lilichuan
 */
public class StreamPageTest {

    static List<Item> list = List.of(new Item("A"), new Item("B"), new Item("C")
            , new Item("AA"), new Item("BB"), new Item("CC")
            , new Item("AAA"), new Item("BBB"), new Item("CCC"));

    public static void main(String[] args) {

        list = list.stream().sorted(Comparator.comparing(Item::getName)).collect(Collectors.toList());
        list.forEach(System.out::println);
        System.out.println("===========================");

        //物理分页截取
        List<Item> collect1 = list.stream().skip(0).limit(1).collect(Collectors.toList());
        List<Item> collect2 = list.stream().skip(3).limit(1).collect(Collectors.toList());
        collect1.forEach(System.out::println);
        collect2.forEach(System.out::println);
        System.out.println("===========================");

        List<Item> collect = list.stream().limit(3).skip(2).collect(Collectors.toList());



        int pageSize = 10;
        int pageIndex = 7;

        List<Integer> expected = List.of(61, 62, 63, 64, 65, 66, 67, 68, 69, 70);
        List<Integer> result = Stream.iterate(1, i -> i + 1)
                .skip((pageIndex - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
        System.out.println(result);

        Stream.iterate(1,i->i+1).limit(10).forEach(System.out::println);


    }
}


