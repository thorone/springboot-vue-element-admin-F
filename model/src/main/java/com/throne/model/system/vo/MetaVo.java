package com.throne.model.system.vo;

import lombok.Data;

@Data
public class MetaVo {
    private String title;
    private String icon;

    public MetaVo() {
    }

    public MetaVo(String title, String icon) {
        this.title = title;
        this.icon = icon;
    }
}
