package com.slippery.shortener.service;

import com.slippery.shortener.dto.UserDto;
import com.slippery.shortener.models.Users;

public interface UserService {
    UserDto login(Users user);
    UserDto register(Users user);
    UserDto findById(Long userId);
    UserDto updateUser(Users userDetails,Long id);
    UserDto deleteById(Long id);
    UserDto findAllUsers();


}
