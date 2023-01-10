package com.throne.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.throne.model.system.entity.Menu;
import com.throne.model.system.vo.RouterVo;

import java.util.List;

public interface MenuService extends IService<Menu> {
    List<Menu> getMenuListByRoleId(Long roleId);

    List<Menu> getMenuTreeList();

    List<RouterVo> getRouterListByUserId(Long userId);

    List<String> getPermsListByUserId(long longValue);
}
