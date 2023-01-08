package com.throne.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.throne.model.system.entity.Role;
import com.throne.model.system.entity.RoleMenu;
import com.throne.system.mapper.RoleMapper;
import com.throne.system.mapper.RoleMenuMapper;
import com.throne.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    @Transactional
    public void addRole(Role role) {
        //创建role对象
        roleMapper.insert(role);
        List<Long> menuIdList = role.getMenuIdList();
        //判断权限列表
        if (menuIdList.size()>0){
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(role.getRoleId());
            for (Long menuId : menuIdList) {
                roleMenu.setMenuId(menuId);
                roleMenuMapper.insert(roleMenu);
            }
        }

    }
}
