package com.throne.system.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.throne.model.system.entity.User;
import com.throne.model.system.vo.LoginVo;
import com.throne.system.mapper.UserMapper;
import com.throne.system.service.UserService;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{
    @Autowired
    private UserMapper userMapper;

    public List<User> getUserByParam() {
        IPage<User> userPage = new Page<User>();
        userPage.setCurrent(1);
        userPage.setSize(1);
        userMapper.selectPage(userPage, null);
        return userPage.getRecords();
    }

    @Override
    public User login(LoginVo loginVo) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_name",loginVo.getUsername());
        queryWrapper.eq("password", SecureUtil.md5(loginVo.getPassword()));
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }
}
