package com.example.demo.examples.controller;

import com.example.demo.examples.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class HelloController {

    @GetMapping("/example")
    String home() {
        return "example";
    }

    @GetMapping("/user")
    String getUser(Model model) {
        User userFromDatabase = new User("Marcin", "P");
        model.addAttribute("example", "To jest jakiś string");
        model.addAttribute("user", userFromDatabase);
        model.addAttribute("imageUrl", "https://lastomine.files.wordpress.com/2017/08/mac582pa-1.jpg?w=371&h=641");
        model.addAttribute("number", ThreadLocalRandom.current().nextInt(-3, 4));
        return "user";
    }

    @GetMapping("/users")
    String getUsers(Model model) {
        List<User> users = new ArrayList<>();
        users.add(new User("Jan", "Kowalski"));
        users.add(new User("Karol", "Kowal"));
        users.add(new User("Piotr", "Nowak"));

        model.addAttribute("users", users);
        return "users";
    }
}
