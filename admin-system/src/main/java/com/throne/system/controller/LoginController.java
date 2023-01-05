package com.throne.system.controller;

import com.throne.model.system.entity.User;
import com.throne.model.system.vo.LoginVo;
import com.throne.system.service.UserService;
import com.throne.system.utils.exception.ThroneException;
import com.throne.system.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/system/")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo){
        if (loginVo !=null){
            User user = userService.login(loginVo);
            if (user != null){
                String status = user.getStatus();
                if ("1".equals(status)){
                    throw new ThroneException(20001,"请填写正确的账号和密码");
                }
                Map<String,Object> map = new HashMap<>();
                map.put("token", "admin-token");
                return Result.ok(map);
            }
        }
        throw new ThroneException(20001,"请填写正确的账号和密码");
    }
    @GetMapping("user/info")
    public Result userInfo(@RequestParam("token") String token){
        System.out.println(token);
        Long id = 100L;
        User user = userService.getById(id);
        Map<String,Object> map = new HashMap<>();
        map.put("avatar",user.getAvatar());
        map.put("introduction",user.getRemark());
        map.put("name",user.getUserName());
        map.put("role","[admin]");
        return Result.ok(map);
    }


}
