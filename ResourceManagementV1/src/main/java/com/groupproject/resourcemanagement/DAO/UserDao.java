package com.groupproject.resourcemanagement.dao;

import com.groupproject.resourcemanagement.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserEntity, Integer> {

}
