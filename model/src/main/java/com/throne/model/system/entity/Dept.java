package com.throne.model.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author throne
 * @since 2023-01-04
 */
@Data
@TableName("sys_dept")
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 部门id
     */
    @TableId(value = "dept_id", type = IdType.AUTO)
    private Long deptId;

    /**
     * 父部门id
     */
    private Long parentId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 显示排序
     */
    private Integer orderNum;

    /**
     * 部门负责人
     */
    private String leader;

    /**
     * 部门练习电话
     */
    private String phone;

    /**
     * 部门状态 0正常 1停用
     */
    private String status;

    /**
     * 删除状态 0正常 1删除
     */
    private String delFlag;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;


}
