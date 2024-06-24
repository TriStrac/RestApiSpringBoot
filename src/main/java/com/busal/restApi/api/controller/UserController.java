package com.busal.restApi.api.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.busal.restApi.api.controller.model.User;
import com.busal.restApi.service.UserService;

@RestController
public class UserController{

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/user")
    public User getUser(@RequestParam Integer id){
        Optional<User> user = userService.getUser(id);
        if(user.isPresent()){
            return (User) user.get();
        }
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, String> loginData) {

        String username = loginData.get("username");
        String password = loginData.get("password");

        Map<String, String> response = new HashMap<>();

        if (username.trim().isEmpty() || password.trim().isEmpty()) {
            response.put("Message", "Either of the parameters is null");
            return ResponseEntity.badRequest().body(response);
        }

        User user = userService.getUserByUsername(username);

        if (user == null || !user.getPassword().equals(password)) {

            // Check if input is default user
            if ("sysarch".equals(username) && "12345678".equals(password)) {
                response.put("Message", "Log in successfully");
                return ResponseEntity.ok(response);
            } else {
                response.put("Message", "Invalid Credentials");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        }

        response.put("Message", "Log in successfully");
        return ResponseEntity.ok(response);
    }
}