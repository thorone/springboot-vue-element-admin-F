package com.throne.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.throne.model.system.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    List<Long> getRoleListByUserId(@Param("id") Long id);
}
