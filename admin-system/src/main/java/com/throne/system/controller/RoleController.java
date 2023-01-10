package com.throne.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.throne.model.system.entity.Role;
import com.throne.system.service.RoleService;
import com.throne.system.utils.exception.ThroneException;
import com.throne.system.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 新增角色
     * @param role
     * @return
     */
    @PostMapping("/addRole")
    public Result addRole(@Validated @RequestBody Role role){
        if (role != null){
            roleService.addRole(role);
            return Result.ok();
        }
        throw new ThroneException(200001,"创建失败,请练习管理员");
    }

    /**
     * 根据roleId删除角色
     * @param roleId
     * @return
     */
    @DeleteMapping("/removeRoleById/{roleId}")
    public Result removeRoleById(@PathVariable Long roleId){
        if (roleId != null){
            roleService.removeById(roleId);
            return Result.ok();
        }
        throw new ThroneException(200001,"删除失败,请练习管理员");
    }

    /**
     * 修改角色
     * @param role
     * @return
     */
    @PostMapping("updateRole")
    public Result updateRole(@Validated @RequestBody Role role){
        roleService.updateById(role);
        return Result.ok();
    }

    @GetMapping("/getRoleById/{roleId}")
    public Result getRoleById(@PathVariable Long roleId){
        Role role = roleService.getById(roleId);
        return Result.ok(role);
    }

    @PostMapping("/getRoleListByParams/{page}/{limit}")
    public Result getRoleListByParams(@PathVariable Integer page,
                                      @PathVariable Integer limit,
                                      @RequestBody Role role){
        IPage<Role> rolePage = new Page<Role>();
        rolePage.setCurrent(page);
        rolePage.setSize(limit);
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if (role != null){
            if (!StringUtils.isEmpty(role.getRoleName())){
                queryWrapper.eq("role_name",role.getRoleName());
            }
            if ((!StringUtils.isEmpty(role.getRemark()))){
                queryWrapper.eq("remark",role.getRemark());
            }
        }
        roleService.page(rolePage,queryWrapper);
        return Result.ok(rolePage);
    }

    @GetMapping("/getTotalRole")
    public Result getTotalRole(){
        List<Role> roleItemList = roleService.list(null);
        return Result.ok(roleItemList);
    }
}
