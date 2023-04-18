package spring.boot_security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.boot_security.models.Role;
import spring.boot_security.models.User;
import spring.boot_security.repository.RoleRepository;
import spring.boot_security.service.UserService;
import spring.boot_security.service.UserServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserServiceImpl userService;
    private final RoleRepository roleRepository;

    @Autowired
    public AdminController(UserServiceImpl userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }


    @GetMapping
    public String show(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("user", users);
        return "admin";
    }

    @GetMapping("/{id}/info")
    public String info(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.findOne(id));
        return "info";
    }


    @GetMapping("/newU")
    public ModelAndView newUser() {
        User user = new User();
        ModelAndView mav = new ModelAndView("newU");
        mav.addObject("user", user);
        List<Role> roles = roleRepository.findAll();
        mav.addObject("allRoles", roles);
        return mav;
    }

    @PostMapping
    public String createUser(@ModelAttribute("users") User user) {
        userService.save(user);
        return "redirect:/admin";
    }


    @GetMapping("/{id}/edit")
    public ModelAndView update(@PathVariable("id") long id) {
        User user = userService.findOne(id);
        ModelAndView mav = new ModelAndView("edit");
        mav.addObject("user", user);
        List<Role> roles = roleRepository.findAll();
        mav.addObject("allRoles", roles);
        return mav;
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.update(id, user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

}
