package com.throne.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.throne.model.system.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> getMenuListByRoleId(@Param("roleId") Long roleId);

    List<Menu> getMenuListByUserId(@Param(("userId")) Long userId);

    List<Menu> getPermsListByUserId(@Param("userId") Long userId);


}
