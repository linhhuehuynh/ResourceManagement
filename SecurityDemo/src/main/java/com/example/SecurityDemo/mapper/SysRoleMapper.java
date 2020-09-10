package com.example.SecurityDemo.mapper;

import com.example.SecurityDemo.entity.SysRole;
import org.springframework.web.bind.annotation.Mapping;


@Mapper
public interface SysRoleMapper {
    @Select("SELECT * FROM sysRole WHERE id = #{id}")
    SysRole selectById(Integer id);
}
