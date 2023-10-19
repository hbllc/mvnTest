package com.llc.generate;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author lilichuan
 */
public class GenerateTests {

    public static void main(String[] args) {
        Class<TestData> clz = TestData.class;
        Field[] declaredFields = clz.getDeclaredFields();
        Arrays.stream(declaredFields).forEach(field -> {
            Class<?> type = field.getType();
            Module module = type.getModule();
            System.out.println(JSONObject.toJSONString(module));
            System.out.println(JSONObject.toJSONString(type));
        });

    }




}
