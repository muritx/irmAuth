package com.fullstack.irm.repository;

import com.fullstack.irm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);

}
