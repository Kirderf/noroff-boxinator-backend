package com.experis.no.boxinator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/swagger")
public class RedirectController {
    @GetMapping
    public RedirectView redirectWithUsingRedirectView() {
        return new RedirectView("/swagger-ui/index.html");
    }
}