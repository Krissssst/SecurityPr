package spring.boot_security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.boot_security.models.User;
import spring.boot_security.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String userShow(@CurrentSecurityContext(expression = "authentication?.name") String name, Model model) {
        User user = userService.findByName(name);
        model.addAttribute("user", user);
        return "info";
    }
}
