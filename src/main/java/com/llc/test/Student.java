package com.llc.test;

import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * @author lilichuan
 */
@lombok.Data
@AllArgsConstructor
@Builder
public class Student {

    private String name;
    private Integer age;
    private Integer score;
    private String clazz;
    private String course;


}