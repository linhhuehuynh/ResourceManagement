package com.example.SecurityDemo.service;

import com.example.SecurityDemo.entity.SysUserRole;
import com.example.SecurityDemo.mapper.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleUserService {

        @Autowired
        private SysUserRoleMapper userRoleMapper;

        public List<SysUserRole> listByUserId(Integer userId) {
            return userRoleMapper.listByUserId(userId);
        }
    }

