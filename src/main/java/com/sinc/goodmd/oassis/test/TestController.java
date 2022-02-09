package com.sinc.goodmd.oassis.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/select")
    public List<Test> testSelect(){
        return testService.select();
    }

    @GetMapping("/auth")
    public String testAuth(){
        return "Authentication!!!";
    }

    @GetMapping("/any")
    public String testAny() {
        return "Any";
    }
}
