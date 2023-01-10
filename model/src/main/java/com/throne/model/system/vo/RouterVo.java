package com.throne.model.system.vo;

import lombok.Data;

import java.util.List;

@Data
public class RouterVo {
    /**
     * 路由地址
     */
    private String path;
    /**
     * 组件地址
     */
    private String component;
    /**
     * 路由名称
     */
    private String name;

    private MetaVo meta;
    /**
     * 子集列表
     */
    private List<RouterVo> children;

    /**
     * 是否隐藏路由，当设置 true 的时候该路由不会再侧边栏出现
     */
    private boolean hidden;

    /**
     * 当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
     */
    private Boolean alwaysShow;

}
