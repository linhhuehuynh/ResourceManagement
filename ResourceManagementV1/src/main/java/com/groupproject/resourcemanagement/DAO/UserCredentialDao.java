package com.groupproject.resourcemanagement.dao;

import com.groupproject.resourcemanagement.entity.UserCredentialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface UserCredentialDao extends JpaRepository<UserCredentialEntity, Integer> {
}
