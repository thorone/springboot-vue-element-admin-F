package com.throne.system.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.throne.model.system.entity.User;
import com.throne.model.system.vo.LoginVo;
import com.throne.model.system.vo.SysUserQueryVo;
import com.throne.system.mapper.UserMapper;
import com.throne.system.service.UserService;
import com.throne.system.utils.exception.ThroneException;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Override
    public User getUserByUserName(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_name",username);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

    @Override
    public IPage getUserList(Integer page, Integer limit, SysUserQueryVo userQueryVo) {
        IPage<User> userIPage = new Page<>();
        userIPage.setCurrent(page);
        userIPage.setSize(limit);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(userQueryVo.getUsername())){
            queryWrapper.eq("user_name",userQueryVo.getUsername());
        }
        if (!StringUtils.isEmpty(userQueryVo.getEmail())){
            queryWrapper.eq("email",userQueryVo.getEmail());
        }
        if (!StringUtils.isEmpty(userQueryVo.getPhoneNumber())){
            queryWrapper.eq("phonenumber",userQueryVo.getPhoneNumber());
        }
        userMapper.selectPage(userIPage,queryWrapper);
        return userIPage;
    }

    @Override
    public void addUser(User user) {
        String password = user.getPassword();
        user.setPassword(SecureUtil.md5(password));
        userMapper.insert(user);
    }

    @Override
    public void delUser(String userName) {
        User user = this.getUserByUserName(userName);
        if (user == null){
            throw new ThroneException(20001,"用户不存在，删除失败");
        }
        userMapper.deleteById(user.getUserId());
    }

    @Override
    public void editUser(User user) {
        userMapper.updateById(user);
    }
}
