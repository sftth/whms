package com.summit.whms.inflearn.sample.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("sample/users")
    public String init() {
        return "hello" ;
    }
}
