package com.throne.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.throne.model.system.entity.User;
import com.throne.model.system.vo.LoginVo;
import com.throne.model.system.vo.SysUserQueryVo;

import java.util.List;

public interface UserService extends IService<User>{

    List<User> getUserByParam();

    User login(LoginVo loginVo);

    User getUserByUserName(String username);

    IPage<User> getUserList(Integer page, Integer limit, SysUserQueryVo userQueryVo);

    void addUser(User user);

    void delUser(String userName);

    void editUser(User user);

    User getUserById(Long id);
}
