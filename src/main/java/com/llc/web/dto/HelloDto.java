package com.llc.web.dto;

import lombok.Data;

import java.time.ZonedDateTime;

/**
 * @author lilichuan
 */
@Data
public class HelloDto {

    private ZonedDateTime time;

    private String name;

}
