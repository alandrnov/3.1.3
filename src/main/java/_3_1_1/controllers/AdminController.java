package _3_1_1.controllers;

import _3_1_1.models.User;
import _3_1_1.service.RoleService;
import _3_1_1.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    private UserService service;
    private RoleService roleService;

    public AdminController(UserService service, RoleService roleService) {
        this.service = service;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String admin(Model model, Authentication authentication) {
        User user = service.getUserByLogin(authentication.getName());
        model.addAttribute("currentuser", user);
        model.addAttribute("all_users", service.getAllUsers());

        return "admin";
    }

}
