package com.fullstack.irm.service;

import com.fullstack.irm.dto.UserDto;
import com.fullstack.irm.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByUserName(String userName);

    List<UserDto> findAllUsers();
}
