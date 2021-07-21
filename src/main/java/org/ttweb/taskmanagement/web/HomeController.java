package org.ttweb.taskmanagement.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value = {"/", "/login"})
    public String home(){
        return "index";
    }
}
