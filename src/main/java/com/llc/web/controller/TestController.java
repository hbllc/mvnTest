package com.llc.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.llc.web.dto.HelloDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lilichuan
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/hello")
    @ResponseBody
    public String hello(@RequestBody HelloDto dto) {
        System.out.println(JSONObject.toJSONString(dto));
        return JSONObject.toJSONString(dto);
    }

}
