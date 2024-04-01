package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.dao.UserDaoImp;
import ru.kata.spring.boot_security.demo.model.User;

import java.security.Principal;


@Controller
@RequestMapping("/user")
public class UserController {

    private final UserDaoImp userDaoImp;

    @Autowired
    public UserController(UserDaoImp userDaoImp) {
        this.userDaoImp = userDaoImp;
    }

    @GetMapping("/user")  // получаем двнные о текущем пользователе
    public String showUserView(Model model, Principal principal) {
        User user = userDaoImp.getUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }
}