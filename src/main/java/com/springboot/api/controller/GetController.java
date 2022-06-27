package com.springboot.api.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/get-api")
public class GetController {

    @ApiOperation(value = "GET 메소드 예제", notes = "@RequestParam을 활용한 GET Method")
    @GetMapping(value = "/request1")
    public String getRequestParam1(
                                @ApiParam(value = "이름", required = true) @RequestParam String name,
                                @ApiParam(value = "이메일", required = true) @RequestParam String email,
                                @ApiParam(value = "회사", required = true) @RequestParam String organization) {
        return name + " " + email + " " + organization;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String getHello() {
        log.info("getHello 메서드가 호출됨.");

        return "Hello World!";
    }

}
