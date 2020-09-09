package com.example.demoresourcemanagement.dao;

import com.example.demoresourcemanagement.entity.SysRole;

@Mapper
public interface SysRoleMapper {
    @Select("SELECT * FROM sysRole WHERE id = #{id}")
    SysRole selectById(Integer id);
}
