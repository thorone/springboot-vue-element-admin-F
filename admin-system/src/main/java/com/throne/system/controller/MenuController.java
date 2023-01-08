package com.throne.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.throne.model.system.entity.Menu;
import com.throne.system.service.MenuService;
import com.throne.system.utils.exception.ThroneException;
import com.throne.system.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    /**
     * 创建菜单
     * @return
     */
    @PostMapping("/addMenu")
    public Result addMenu(@Validated @RequestBody Menu menu){
        menuService.save(menu);
        return Result.ok();
    }

    /**
     * 根据menuIds删除菜单
     * @param menuId
     * @return
     */
    @DeleteMapping("/removeMenuById/{menuId}")
    public Result removeMenuById(@PathVariable Long menuId){
        return  Result.ok().message("功能暂时不做实现");
    }

    /**
     * 修改菜单信息
     * @param menu
     * @return
     */
    @PostMapping("/editMenu")
    public Result editMenu(@RequestBody Menu menu){
        menuService.updateById(menu);
        return Result.ok();
    }
    @GetMapping("/getMenuById/{menuId}")
    public Result getMenuById(@PathVariable Long menuId){
        if (menuId == null){
            throw new ThroneException(20001,"参数异常，请重新查询");
        }
        Menu menu = menuService.getById(menuId);
        return Result.ok(menu);
    }

    @GetMapping("/getMenuTreeList")
    public Result getMenuTreeList(){
        List<Menu> menuTreeList = menuService.getMenuTreeList();
        return Result.ok(menuTreeList);
    }

    @GetMapping("/getMenuByParams/{page}/{limit}")
    public Result getMenuByParams(@PathVariable Integer page,
                                  @PathVariable Integer limit,
                                  @RequestBody Menu menu){
        IPage<Menu> menuIPage = new Page<>();
        menuIPage.setCurrent(page);
        menuIPage.setSize(limit);
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        if (menu != null){
            if (!StringUtils.isEmpty(menu.getMenuName())){
                queryWrapper.eq("menu_name",menu.getMenuName());
            }
            if (!StringUtils.isEmpty(menu.getPerms())){
                queryWrapper.eq("perms",menu.getPerms());
            }
        }
        menuService.page(menuIPage,queryWrapper);
        return Result.ok(menuIPage);
    }

    @GetMapping("/getMenuListByRoleId/{roleId}")
    public Result getMenuListByRoleId(@PathVariable Long roleId){
        List<Menu> menuList =  menuService.getMenuListByRoleId(roleId);
        return Result.ok(menuList);
    }

}
