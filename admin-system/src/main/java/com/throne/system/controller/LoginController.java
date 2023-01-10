package com.throne.system.controller;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.throne.model.system.entity.User;
import com.throne.model.system.vo.LoginVo;
import com.throne.model.system.vo.RouterVo;
import com.throne.system.service.MenuService;
import com.throne.system.service.UserService;
import com.throne.system.utils.exception.ThroneException;
import com.throne.system.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/system/")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo){
        if (loginVo !=null){
            User user = userService.login(loginVo);
            if (user != null){
                String status = user.getStatus();
                if ("1".equals(status)){
                    throw new ThroneException(20001,"请填写正确的账号和密码");
                }
                Long userId = user.getUserId();
                String userName = user.getUserName();
                Map<String,Object> map = new HashMap<>();
                map.put("userId",userId);
                map.put("userName",userName);
                String token = JWTUtil.createToken(map, "throne".getBytes());
                map.put("token", token);
                return Result.ok(map);
            }
        }
        throw new ThroneException(20001,"请填写正确的账号和密码");
    }
    @GetMapping("user/info")
    public Result userInfo(@RequestParam("token") String token){
        System.out.println(token);
        boolean result = JWTUtil.verify(token, "throne".getBytes());
        if (result){
            /**
             * json 格式化后导致Long 转换成integer
             */
            JWT jwt = JWTUtil.parseToken(token);
            Number userId = (Number) jwt.getPayload("userId");
            User user = userService.getById(userId.longValue());
            Map<String,Object> map = new HashMap<>();
            map.put("avatar",user.getAvatar());
            map.put("introduction",user.getRemark());
            map.put("name",user.getUserName());
            map.put("role","[admin]");
            List<RouterVo> routerListByUserId = menuService.getRouterListByUserId(userId.longValue());
            List<String> permsList =  menuService.getPermsListByUserId(userId.longValue());
            //菜单权限数据
            map.put("routers",routerListByUserId);
            //按钮权限数据
            map.put("buttons",permsList);
            return Result.ok(map);
        }else {
            return Result.fail();
        }

    }

    @PostMapping("/user/logout")
    public Result logout(){
        return Result.ok();
    }


}
