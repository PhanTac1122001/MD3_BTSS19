package com.btss19.controller;

import com.btss19.model.dto.UserLoginDTO;
import com.btss19.model.entity.User;
import com.btss19.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user",new User());
        return "user/register";
    }
    @PostMapping("/register")
    public String handleRegister(@ModelAttribute User user){
        userService.register(user);
        return "redirect:/user/login";
    }
    @GetMapping("/login")
    public String login(Model model, UserLoginDTO userLoginDTO){
        model.addAttribute("user",userLoginDTO);
        return "user/login";
    }
    @PostMapping("/login")
    public String handleLogin(@ModelAttribute("user") UserLoginDTO userLoginDTO, HttpServletRequest request){
        User user=userService.login(userLoginDTO);
        if (user !=null){
            HttpSession session=request.getSession();
        session.setAttribute("userLogin",user);
        return "redirect:/";
        }
        return "user/login";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("userLogin");
        return "redirect:/";
    }
}
