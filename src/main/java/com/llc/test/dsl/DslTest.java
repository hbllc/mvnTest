package com.llc.test.dsl;

import org.apache.commons.lang3.StringUtils;

/**
 * @author lilichuan
 */
public class DslTest {

    public static void main(String[] args) {

        String s = "1234567890";
        String s1 = null;
        String s2 = "";
        String s3 = "   ";

        System.out.println(StringUtils.isNotBlank(s3));
        System.out.println(org.springframework.util.StringUtils.hasText(s3));

    }

}
