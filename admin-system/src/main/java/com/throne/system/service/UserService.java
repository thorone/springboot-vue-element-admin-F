package com.throne.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.throne.model.system.entity.User;

import java.util.List;

public interface UserService extends IService<User>{

    List<User> getUserByParam();
}
