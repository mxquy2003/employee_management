package com.example.employee_management.controller;

import com.example.employee_management.dto.request.UserCreationRequest;
import com.example.employee_management.dto.request.UserUpdateRequest;
import com.example.employee_management.entity.User;
import com.example.employee_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    User createUser(@RequestBody UserCreationRequest request){
        return userService.createUser(request);
    }
    @GetMapping
    List<User> getUser(){
        return userService.getUsers();
    }
    @GetMapping("/{userId}")
    User getUser(@PathVariable("userId") Long userId){
        return userService.getUser(userId);
    }
    @PutMapping("/{userId}")
    User updatUser(@PathVariable Long userId, @RequestBody UserUpdateRequest request){
        return userService.updateUser(userId,request);
    }
    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return "User has been deleted";
    }


}
