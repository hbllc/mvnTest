package com.llc.test.stream;

import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * @author lilichuan
 */
@lombok.Data
@AllArgsConstructor
@Builder
class OutstandingStudent {

    private String name;
    private Integer age;
    private Integer score;
    private String clazz;
    private String course;


}