package com.throne.model.system.vo;

import lombok.Data;

/**
 * 用户查询条件对象
 */
@Data
public class SysUserQueryVo {
    private String username;
    private String email;
    private String phoneNumber;
}
