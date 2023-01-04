package com.throne.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.throne.model.system.entity.User;
import com.throne.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUserByParam")
    public List<User> getUserByParam(){
        return userService.getUserByParam();
    }
}
