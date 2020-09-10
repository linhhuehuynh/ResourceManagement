package com.example.SecurityDemo.mapper;

import com.example.SecurityDemo.entity.SysUserRole;

import java.util.List;

@Mapper
public interface SysUserRoleMapper {
    @Select("SELECT * FROM sysUserRole WHERE user_id = #{userId}")
    List<SysUserRole> listByUserId(Integer userId);
}
