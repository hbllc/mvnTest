package com.llc.util;

import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class TestUtil {

    public static void main(String[] args) {
        List<Integer> list = List.of(121,211);
        Map<Integer,String> map = Map.of(1,"ceshi");

        Object[] obj = {"1erer",231,true,list,map};
        System.out.println(formate("{},{},{},{},{}", "1erer", 231, null, list));
    }

    private static String formate(String messagePattern,Object...objects){
        FormattingTuple format =
                MessageFormatter.arrayFormat(messagePattern, objects);
        return format.getMessage();
    }

}
