package com.project.toyProject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/*")
@Slf4j
public class ApiController {
    @GetMapping("/kakaomap")
    public String map() {
        return "/api/kakaomap";
    }
}
