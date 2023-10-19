package com.llc.test;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.llc.web.dto.HelloDto;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
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

    @Test
    public void test(){
        HelloDto dto = new HelloDto();
        dto.setName("test");
        dto.setTime(ZonedDateTime.now());
        System.out.println(JSONObject.toJSONString(dto));
    }


    @Test
    public void testCompare(){
        // 获取当前时间
        ZonedDateTime now = ZonedDateTime.now();
        Date nowDate = DateUtil.tomorrow();


        // 比较两个时间的差异
        System.out.println("ZonedDateTime和Date的时间差异：");
        System.out.println("相差" + (now.toInstant().toEpochMilli() - nowDate.getTime()) + "毫秒");
        System.out.println("相差" + (now.toInstant().toEpochMilli() - nowDate.getTime()) + "毫秒");

        // 格式化输出时间
        System.out.println("ZonedDateTime时间：" + now);
        System.out.println("Date时间：" + new Date(nowDate.getTime()));
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
