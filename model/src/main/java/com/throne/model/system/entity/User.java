package com.throne.model.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author throne
 * @since 2023-01-04
 */
@TableName("sys_user")
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 登陆账号
     */
    @NotBlank(message = "登陆账号不能为空")
    @TableField(value = "login_name")
    private String loginName;

    /**
     * 用户昵称
     */
    @Size(min = 0,max = 30,message = "用户昵称范围0-30")
    @TableField(value = "user_name")
    private String userName;

    /**
     * 用户密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 用户手机
     */
    private String phonenumber;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户性别 0男 1女 2未知
     */
    private String sex;

    /**
     * 头像路径
     */
    private String avatar;

    /**
     * 部门ID
     */
    @TableField(value = "dept_id")
    private Long deptId;

    /**
     * 超级管理员 1是超级管理员 
     */
    @TableField("isAdmin")
    private String isAdmin;

    /**
     * 账号状态 0启动 1停用
     */
    private String status;

    /**
     * 删除状态 0未删除 1已删除
     */
    @TableField(value = "del_flag")
    @TableLogic
    private String delFlag;

    /**
     * 创建者
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新者
     */
    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;


}
