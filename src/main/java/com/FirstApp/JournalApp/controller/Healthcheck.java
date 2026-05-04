package com.FirstApp.JournalApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Healthcheck {

    @GetMapping("/Health-Check")
    public String healthcheck(){
        return "ok";
    }
}
