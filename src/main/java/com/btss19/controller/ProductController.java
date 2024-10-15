package com.btss19.controller;

import com.btss19.model.entity.Product;
import com.btss19.service.category.CategoryService;
import com.btss19.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String index(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "5") int size) {

        List<Product> products = productService.findAll(page,size);
        model.addAttribute("products", products);
        model.addAttribute("page",page);
        model.addAttribute("size",size);
        model.addAttribute("totalPage",productService.totalPages(size));
        return "product/index";
    }

    @GetMapping("/add")
    public String add(Model model, Product product) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("product", product);
        return "product/add";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Product product, BindingResult result) {
        if (result.hasErrors()) {
            for (ObjectError allError : result.getAllErrors()) {
                System.out.println(allError.getDefaultMessage());
            }
            return "product/add";
        }

        productService.newAdd(product);
        return "redirect:/product";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable int id) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.findAll());
        return "product/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable int id, @ModelAttribute Product product) {
        product.setId(id);
        if (productService.update(product)) {
            return "redirect:/product";
        }
        return "product/edit" + id;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        productService.delete(id);
        return "redirect:/product";
    }



}
