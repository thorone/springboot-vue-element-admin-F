package com.throne.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.throne.model.system.entity.User;
import com.throne.model.system.vo.SysUserQueryVo;
import com.throne.system.service.UserService;
import com.throne.system.utils.exception.ThroneException;
import com.throne.system.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
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


    @PostMapping("getUserList/{page}/{limit}")
    public Result getUserList(@PathVariable Integer page,
                              @PathVariable Integer limit,
                              @RequestBody SysUserQueryVo userQueryVo){
        //需要有参数 展示不添加
        if (page == null || limit == null){
            throw new ThroneException(20001,"参数不正确");
        }
        IPage<User> userPage = userService.getUserList(page, limit,userQueryVo);
        return Result.ok(userPage);
    }

    @PostMapping("/addUser")
    public Result addUser(@RequestBody User user){
        if (user != null) {
            userService.addUser(user);
            return Result.ok();
        }
        throw new ThroneException(20001,"参数不正确");
    }

    @DeleteMapping("/delUser/{userName}")
    public Result delUser(@PathVariable String userName){
        if (!StringUtils.isEmpty(userName)){
            userService.delUser(userName);
            return Result.ok();
        }
        throw new ThroneException(20001,"参数不正确");
    }

    @PostMapping("/editUser")
    public Result editUser(@RequestBody User user){
        if (user !=null ){
            userService.editUser(user);
            return Result.ok();
        }
        throw new ThroneException(20001,"参数不正确");
    }

    @GetMapping("/getUserById/{id}")
    public Result getUserById(@PathVariable Long id){
        //TODO
        return null;
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
