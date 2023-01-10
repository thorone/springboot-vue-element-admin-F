package com.throne.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.throne.model.system.entity.Menu;
import com.throne.model.system.entity.RoleMenu;
import com.throne.model.system.entity.User;
import com.throne.model.system.vo.MetaVo;
import com.throne.model.system.vo.RouterVo;
import com.throne.system.mapper.MenuMapper;
import com.throne.system.mapper.RoleMenuMapper;
import com.throne.system.service.MenuService;
import com.throne.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private UserService userService;
    @Override
    public List<Menu> getMenuListByRoleId(Long roleId) {
        //获取全部菜单列表
        List<Menu> menus = menuMapper.selectList(null);
        //获取角色菜单列表
        QueryWrapper<RoleMenu> roleMenuQueryWrapper = new QueryWrapper<>();
        roleMenuQueryWrapper.eq("role_id",roleId);
        List<RoleMenu> roleMenus = roleMenuMapper.selectList(roleMenuQueryWrapper);
        List<Long> menuIds = new ArrayList<>();
        for (RoleMenu roleMenu : roleMenus) {
            menuIds.add(roleMenu.getMenuId());
        }
        //设置isSelected
        for (Menu menu : menus) {
            if (menuIds.contains(menu.getMenuId())){
                menu.setIsSelect(true);
            }
        }
        //获取父级菜单列表
        List<Menu>  parentMenuList =  setParentMenu(menus,0L);
        //递归处理
        List<Menu>  menuTree=createMenuTree(parentMenuList,menus);
        return menuTree;
    }

    @Override
    public List<Menu> getMenuTreeList() {
        List<Menu> menus = menuMapper.selectList(null);
        List<Menu> parentMenu = setParentMenu(menus, 0L);
        List<Menu> menuTree = this.createMenuTree(parentMenu, menus);
        return menuTree;
    }

    private List<Menu> createMenuTree(List<Menu> parentMenuList, List<Menu> menus) {
        for (Menu parentMenu : parentMenuList) {
            List<Menu> subMenuList = new ArrayList<>();
            for (Menu subMenu : menus) {
                if (parentMenu.getMenuId() == subMenu.getParentId()){
                    subMenuList.add(subMenu);
                }
            }
            parentMenu.setChildren(subMenuList);
            createMenuTree(subMenuList,menus);
        }
        return parentMenuList;

    }

    private List<Menu> setParentMenu(List<Menu> menus,Long parentId) {
        List<Menu> parentMenuList =  new ArrayList<>();
        for (Menu menu : menus) {
            if (menu.getParentId() == parentId){
                parentMenuList.add(menu);
            }
        }
        return parentMenuList;
    }

    @Override
    public List<String> getPermsListByUserId(long userId) {
        ArrayList<String> permsList = new ArrayList<>();
        User user = userService.getUserById(userId);
        if ("1".equals(user.getIsAdmin())){
            //获取所有权限
            List<Menu> menus = menuMapper.selectList(null);
            for (Menu menu : menus) {
                if ("F".equals(menu.getMenuType())){
                    permsList.add(menu.getPerms());
                }
            }
        }else{
            //根据userId获取菜单列表
            List<Menu> permsListByUserId = menuMapper.getPermsListByUserId(userId);
            for (Menu menu : permsListByUserId) {
                if ("F".equals(menu.getMenuType())){
                    permsList.add(menu.getPerms());
                }
            }
        }
        return permsList;
    }

    public List<RouterVo> getRouterListByUserId(Long userId){
        User user = userService.getUserById(userId);
        List<Menu>  menuList=null;
        List<Menu> menuTree = null;
        if ("1".equals(user.getIsAdmin())){
            QueryWrapper<Menu> menuQueryWrapper = new QueryWrapper<>();
            menuQueryWrapper.ne("menu_type","F");
            menuList = menuMapper.selectList(menuQueryWrapper);
        }else{
            menuList= menuMapper.getMenuListByUserId(userId);
        }
        List<Menu> parentMenu = this.setParentMenu(menuList,0L);
        menuTree = createMenuTree(parentMenu,menuList);
        //构建RouterVo
        ArrayList<RouterVo> routerVoTree = this.createRouterVoTree(menuTree);
        return routerVoTree;

    }

    private ArrayList<RouterVo> createRouterVoTree(List<Menu> menuTree) {
        ArrayList<RouterVo> routerVos = new ArrayList<>();
        for (Menu menu : menuTree) {
            RouterVo routerVo = new RouterVo();
            routerVo.setPath(menu.getPath());
            routerVo.setName(menu.getPath());
            routerVo.setComponent(menu.getUrl());
            routerVo.setHidden(false);
            routerVo.setAlwaysShow(false);
            routerVo.setMeta(new MetaVo(menu.getMenuName(),menu.getIcon()));
            if (menu.getChildren().size()>0){
                ArrayList<RouterVo> routerVoTree = this.createRouterVoTree(menu.getChildren());
                routerVo.setChildren(routerVoTree);
            }
            routerVos.add(routerVo);
        }
        return routerVos;

    }
}
