package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public AdminController(RoleService roleService, UserService userService, PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/newUserCreate")    // отображаем страницу создания нового пользователя +
    public String showCreateUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        List<Role> roleList = roleService.getAllRoles();
        model.addAttribute("roleList", roleList);
        return "newUserCreate";
    }

    @PostMapping("/newUserCreate")    //создаем и сохраняем нового пользователя +
    public String createAndSaveUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roleList", roleService.getAllRoles());
        userService.addUser(user);
        return "redirect:/admin/";
    }

    @GetMapping()   // отображение списка всех пользователей
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUser());
        return "admin";
    }

    @GetMapping("/user/{id}")  // получаем текущего пользователя и передаем в представление
    public String showUserForm(Model model, User admin) {
        model.addAttribute("admin", admin);
        model.addAttribute("users", userService.getAllUser());
        model.addAttribute("userRoles", roleService.getAllRoles());
        return "admin";
    }

    @GetMapping("user/{id}/editUser") // получаем инфу о юзере по id и передаем в представление
    public String editUserForm(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("roleList", userService.getAllUser());
        return "editUser";
    }

    @PostMapping("editUser/{id}") // обновляем информацию о пользователе
    public String saveUser(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    @PostMapping("/delete/{id}")  // +
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}