package com.bookstore.bookstore_api;

// import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// import com.bookstore.bookstore_api.UserService;


@RestController
@RequestMapping("/api/Users")
public class UserController {

    @Autowired
    private UserService userService;

    // Post mapping to create a User with username, password and optional fields (name, email address, home)
    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    // Get mapping to retriece a User Object and its fields by their username
    @GetMapping("/username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }
    

    }