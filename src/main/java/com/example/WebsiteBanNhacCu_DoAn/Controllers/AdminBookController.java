package com.example.WebsiteBanNhacCu_DoAn.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Admin")
public class AdminBookController {

    @GetMapping("")
    public String adminBook() {
        return "/BookApi/index";
    }
}
