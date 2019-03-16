package com.stasuma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @RequestMapping(value = "/main_page", method = RequestMethod.GET)
    public String mainPage(){
        return "main";
    }
}
