package com.llc.test;

import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

/**
 * @author lilichuan
 */
public class A {
    public static void main(String[] args) {
        String s1 = null;
        String s = get("1");
    }



    @NonNull
    public static String get(@NonNull String url){
        return StringUtils.equals(url, "1") ? null: "2";
    }

}


