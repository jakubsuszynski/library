package com.jsuszynski.library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MVCController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/search")
    public String search() {
        return "search";
    }

    @RequestMapping("/browse")
    public String browse() {
        return "browse";
    }
}
