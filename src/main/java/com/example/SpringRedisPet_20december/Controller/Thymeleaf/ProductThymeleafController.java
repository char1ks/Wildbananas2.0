package com.example.SpringRedisPet_20december.Controller.Thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductThymeleafController {

    @GetMapping("/main")
    public String mainPage(){
        return "product/main";
    }
    @GetMapping("/concrete/{id}")
    public String concretePage(){
        return "product/concrete";
    }
    @GetMapping("/new")
    public String newPage(){
        return "product/new";
    }
}
