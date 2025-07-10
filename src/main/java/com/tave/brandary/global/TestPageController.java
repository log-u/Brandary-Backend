package com.tave.brandary.global;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestPageController {
    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("location", "https://example.com");
        return "login"; // templates/login.html
    }
}