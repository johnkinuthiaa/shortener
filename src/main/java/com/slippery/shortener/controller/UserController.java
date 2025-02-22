package com.slippery.shortener.controller;

import com.slippery.shortener.dto.UrlDto;
import com.slippery.shortener.dto.UserDto;
import com.slippery.shortener.models.Users;
import com.slippery.shortener.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody Users user){
        return ResponseEntity.ok(service.login(user));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody Users users) {
        return ResponseEntity.ok(service.register(users));
    }

    @PutMapping("/{userId}/update")
    public ResponseEntity<UserDto> updateUsers(@RequestBody Users users, @PathVariable Long userId) {
        return ResponseEntity.ok(service.updateUser(users, userId));
    }

    @GetMapping("/{id}/get")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<UserDto> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<UserDto> getAll() {
        return ResponseEntity.ok(service.findAllUsers());
    }

    @PutMapping("/{userId}/upload-profile")
    public ResponseEntity<UserDto> uploadImage(@PathVariable Long userId,@RequestPart MultipartFile profilePhoto) throws IOException {
        return ResponseEntity.ok(service.uploadProfilePhoto(profilePhoto, userId));
    }

    @GetMapping("/fetch-profile")
    public ResponseEntity<byte[]> fetchProfileImage(@RequestParam Long userId) {
        return ResponseEntity.ok(service.fetchProfileImage(userId));
    }

}
