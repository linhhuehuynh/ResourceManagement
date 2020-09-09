package com.example.demoresourcemanagement.dao;

import com.example.demoresourcemanagement.entity.SysUserRole;

import java.util.List;

@Mapper
public interface SysUserRoleMapper {
    @Select("SELECT * FROM sysUserRole WHERE user_id = #{userId}")
    List<SysUserRole> listByUserId(Integer userId);
}
