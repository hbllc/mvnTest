package com.llc.test.dsl;

import java.text.NumberFormat;

/**
 * @author lilichuan
 */
public class DslTest {

    public static void main(String[] args) {


        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        nf.setMaximumIntegerDigits(4);
        nf.setMinimumIntegerDigits(4);
        System.out.println(nf.format(1));

    }

}
