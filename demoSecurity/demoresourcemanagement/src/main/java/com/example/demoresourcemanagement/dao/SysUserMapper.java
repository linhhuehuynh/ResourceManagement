package com.example.demoresourcemanagement.dao;

import com.example.demoresourcemanagement.entity.SysUser;
import org.xmlunit.util.Mapper;

@Mapper
public interface SysUserMapper {
    @Select("SELECT * FROM sysUser WHERE id = #{id}")
    SysUser selectById(Integer id);

    @Select("SELECT * FROM sysUser WHERE username = #{name}")
    SysUser selectByName(String name);
}
