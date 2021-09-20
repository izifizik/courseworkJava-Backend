package com.api.courswork.handles;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class router {

    @RequestMapping("/api/hello")
    public String getString() {
        return "asdasd";
    }
}
