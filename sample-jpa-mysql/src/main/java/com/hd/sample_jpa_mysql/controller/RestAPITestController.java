package com.hd.sample_jpa_mysql.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestAPITestController {
    @GetMapping("/hello")
    public String getHello() {
        return "안녕하세요. 스트링부트입니다.";
    }

    @GetMapping("/board/{variable}")
    public String getVariable(@PathVariable String variable) {
        return variable;
    }

    @GetMapping("/req") // RequestParam : query로 값을 전달하는 방식
    public String getReqParam(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String company
    ) {
        return name + " " + email + " " + company;
    }

}
