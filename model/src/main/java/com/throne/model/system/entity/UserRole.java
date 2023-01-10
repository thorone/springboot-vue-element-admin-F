package com.throne.model.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "sys_user_role")
public class UserRole {

    @TableId(value = "user_id",type = IdType.INPUT)
    private Long userId;

    @TableField(value = "role_id")
    private Long roleId;
}
