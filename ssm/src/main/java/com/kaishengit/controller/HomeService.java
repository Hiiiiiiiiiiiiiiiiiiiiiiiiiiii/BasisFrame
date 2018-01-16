package com.kaishengit.controller;

import com.kaishengit.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeService {
    @Autowired
    ProductService productService;

    @GetMapping("/")
    public String home(){
        int size = productService.findAll().size();
        System.out.println(size);
        return "index";
    }
}
