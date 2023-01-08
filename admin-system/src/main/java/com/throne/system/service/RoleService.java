package com.throne.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.throne.model.system.entity.Role;
import com.throne.system.mapper.RoleMapper;

public interface RoleService extends IService<Role> {
    void addRole(Role role);
}
