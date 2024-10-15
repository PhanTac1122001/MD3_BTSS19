package com.btss19.controller;

import com.btss19.model.entity.Category;
import com.btss19.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public String index(Model model){
        List<Category> categories=categoryService.findAll();
        model.addAttribute("categories",categories);
        return "category/index";
    }

    @GetMapping("/add")
    public String add(Model model,Category category){
        model.addAttribute("category",category);
        return "category/add";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute Category category, BindingResult result){
        if (result.hasErrors()){
            for (ObjectError allError : result.getAllErrors()){
                System.out.println(allError.getDefaultMessage());
            }
            return "category/add";
        }
        categoryService.newAdd(category);
        return "redirect:/category";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable int id){
        Category category=categoryService.findById(id);
        model.addAttribute("category",category);
        return "category/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable int id,@ModelAttribute Category category){
        category.setId(id);
        if (categoryService.update(category)){
            return "redirect:/category";
        }
        return "category/edit"+id;
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        categoryService.delete(id);
        return "redirect:/category";
    }
}
