package com.throne.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.throne.model.system.entity.Menu;

import java.util.List;

public interface MenuService extends IService<Menu> {
    List<Menu> getMenuListByRoleId(Long roleId);

    List<Menu> getMenuTreeList();
}
