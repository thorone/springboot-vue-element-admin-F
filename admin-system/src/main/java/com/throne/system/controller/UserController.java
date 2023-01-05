package com.throne.system.controller;


import com.throne.model.system.entity.User;
import com.throne.system.service.UserService;
import com.throne.system.utils.exception.ThroneException;
import com.throne.system.utils.result.Result;
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

    @GetMapping("/error")
    public Result getError(){
        int i = 1/0;
        return Result.ok();
    }

    @GetMapping("/error2")
    public Result gerError2(){
        try {
            int i = 1/ 0;
        } catch (Exception e) {
            throw new ThroneException(20001,"自定义异常,请联系管理员");
        }
        return Result.ok();
    }
}
