package com.throne.model.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "sys_role_menu")
public class RoleMenu {
    @TableId(value = "role_id",type = IdType.INPUT)
    private Long roleId;
    @TableField(value = "menu_id")
    private Long menuId;
}
