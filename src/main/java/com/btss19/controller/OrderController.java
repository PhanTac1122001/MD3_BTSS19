package com.btss19.controller;

import com.btss19.model.entity.Order;
import com.btss19.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private ProductService productService;


    @GetMapping("/productList")
    public String findAllProduct(Model model){
        model.addAttribute("product",productService.findAllProduct());
        return "product-list";
    }
//    @PostMapping("")
}
