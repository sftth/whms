package com.summit.whms.inflearn.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @Autowired
    private SampleService sampleService;

    @Autowired
    private LessonService lessonService;


    @GetMapping("/sample/hello")
    public String hello() {
        return "hello " + sampleService.getName();
    }

    @GetMapping("/get/hello")
    public String getHello() {
        return "hello " + lessonService.getName();
    }
}
