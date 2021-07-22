package org.ttweb.taskmanagement.web.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value = {"/", "/login", "/register"})
    public String home(){
        return "index";
    }
}
