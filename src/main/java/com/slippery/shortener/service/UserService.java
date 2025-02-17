package com.slippery.shortener.service;

import com.slippery.shortener.dto.UserDto;
import com.slippery.shortener.models.Users;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    UserDto login(Users user);
    UserDto register(Users user);
    UserDto uploadProfilePhoto(MultipartFile profilePhoto,Long userId) throws IOException;
    byte[] fetchProfileImage(Long userId);
    UserDto findById(Long userId);
    UserDto updateUser(Users userDetails,Long id);
    UserDto deleteById(Long id);
    UserDto findAllUsers();


}
