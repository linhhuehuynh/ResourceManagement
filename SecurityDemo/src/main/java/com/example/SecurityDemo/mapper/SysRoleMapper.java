package com.example.SecurityDemo.mapper;

import com.example.SecurityDemo.entity.SysRole;

@Mapper
public interface SysRoleMapper {
    @Select("SELECT * FROM sysRole WHERE id = #{id}")
    SysRole selectById(Integer id);
}
