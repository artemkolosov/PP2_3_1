package web.controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import web.service.UserService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

@Controller
public class UserController {


    private final UserService userService;


    public UserController(UserService userService) {
    this.userService = userService;
    }


//INTRO

@GetMapping(value = "/")

    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("CRUD приложение");
        messages.add("Демонстрация основных методов");
        messages.add("февраль 2024");
        messages.add("Студент Колосов Артём");
        model.addAttribute("messages", messages);
        return "index";
    }

//Create


    @GetMapping("/useradd")
    public String newUser(@ModelAttribute("user") User user) {
        return "/useradd";
    }



    @PostMapping("/useradd")
    public String create(@ModelAttribute("user") User user) {
        userService.createUser(user);
        return "redirect:/users";
    }


//Read
    @GetMapping(value = "/users")

    public String allUsersPage(ModelMap model) {
        List<User> allUsers = userService.readAllUsers();
        model.addAttribute("users", allUsers);
        return "users";

    }


//Update



    @GetMapping("/useredit")
    public String editUser(Model model, @RequestParam("id") Long id) {
        model.addAttribute("user", userService.readUser(id));
        System.out.println(userService.readUser(id));
        return "/useredit";
    }



//    @PostMapping("/useredit")
//    public String update(Model model, @RequestParam("id") Long id) {
//        model.addAttribute("user", userService.readUser(id));
//        user.setId(id);
//        userService.updateUser(user);
//        return "redirect:/users";
//    }

    @PostMapping("/useredit")
    public String saveUser(@ModelAttribute("user") @Valid User user,
                           BindingResult bindingResult,
                           @RequestParam("id") Long id) {
        if (bindingResult.hasErrors()) {
            if (id > 0) {
                user.setId(id);
            }
            return "useredit";

        }
        if (user.getId() == 0) {
            userService.createUser(user);
        } else {
            userService.updateUser(user, id);
        }

        return "redirect:/users";
    }



//    @PostMapping("/useredit")
//    public String update(@ModelAttribute("user") @Valid User user) {
//        userService.updateUser(user);
//        return "redirect:/users";
//    }


//Delete



    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }


}