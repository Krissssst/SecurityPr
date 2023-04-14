package spring.boot_security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.boot_security.models.User;
import spring.boot_security.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public String show(Model model){
        List<User> users=userService.findAll();
        model.addAttribute("users",users);
        return "show";
    }
    @GetMapping("/show/{id}/info")
    public String  info(@PathVariable("id") long id,Model model){
        model.addAttribute("users",userService.findOne(id));
        return "info";
    }
    @GetMapping("/newU")
    public String addNewUser(Model model){
        model.addAttribute("users",new User());
        return "newU";
    }
    @PostMapping()
    public String createUser(@ModelAttribute("users") User user){
        userService.save(user);
        return "redirect:/admin";
    }
    @GetMapping("/show/{id}/edit")
    public String edit(Model model,@PathVariable("id") long id){
        model.addAttribute("user",userService.findOne(id));
        return "edit";
    }
    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") User user,@PathVariable("id") long id){
        userService.update(id,user);
        return "redirect:/admin";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id){
        userService.delete(id);
        return "redirect:/admin";
    }



}
