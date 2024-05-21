package com.ra.session20_sessioncookies.controller;

import com.ra.session20_sessioncookies.dto.request.FormLogin;
import com.ra.session20_sessioncookies.dto.request.FormRegister;
import com.ra.session20_sessioncookies.model.User;
import com.ra.session20_sessioncookies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController
{
    private final UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping({"/", "/home"})
    public String home()
    {
        return "home";
    }

    @GetMapping("/register")
    public String initRegister(Model model)
    {
        model.addAttribute("register", new FormRegister());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute FormRegister formRegister)
    {
        userService.registerUser(formRegister);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String initLogin(Model model)
    {
        model.addAttribute("login", new FormLogin());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute FormLogin formLogin, HttpSession session)
    {
        User userLogin = userService.loginUser(formLogin);
        session.setAttribute("user", userLogin);
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session)
    {
        session.invalidate();
        return "redirect:/home";
    }
}
