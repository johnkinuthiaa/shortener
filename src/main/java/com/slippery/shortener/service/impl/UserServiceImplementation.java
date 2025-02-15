package com.slippery.shortener.service.impl;

import com.slippery.shortener.dto.UserDto;
import com.slippery.shortener.models.Users;
import com.slippery.shortener.repository.UserRepository;
import com.slippery.shortener.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    private final UserRepository repository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder =new BCryptPasswordEncoder(12);

    public UserServiceImplementation(UserRepository repository, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDto login(Users user) {
        UserDto response =new UserDto();
        Users users =repository.findByUsername(user.getUsername());
        if(users ==null){
            response.setMessage("User not found");
            response.setStatusCode(401);
            return response;
        }

        Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                users.getUsername(),users.getPassword()
        ));
        if(authentication.isAuthenticated()){
            response.setMessage("User logged in");
            response.setStatusCode(200);
            return response;
        }
        response.setMessage("User not authenticated");
        response.setStatusCode(401);
        return response;
    }

    @Override
    public UserDto register(Users user) {
        UserDto response =new UserDto();
        Users users =repository.findByUsername(user.getUsername());
        Users existingEmail =repository.findByEmail(user.getEmail());
        if(user.getEmail() ==null){
            response.setMessage("email cannot be null!");
            response.setStatusCode(200);
            return response;
        }
        if(user.getUsername().isEmpty()){
            response.setMessage("Username cannot be null!");
            response.setStatusCode(200);
            return response;
        }
        if(user.getPassword().isEmpty()){
            response.setMessage("password is null!");
            response.setStatusCode(200);
            return response;
        }
        if(users !=null){
            response.setMessage("user with the username already exists");
            response.setStatusCode(200);
            return response;
        }
        if(existingEmail != null){
            response.setMessage("user with the email already exists");
            response.setStatusCode(200);
            return response;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        response.setMessage("User registered successfully!");
        response.setStatusCode(200);
        response.setUser(user);
        repository.save(user);
        return response;
    }

    @Override
    public UserDto findById(Long userId) {
        UserDto response =new UserDto();
        Optional<Users> existingUser =repository.findById(userId);
        if(existingUser.isEmpty()){
            response.setMessage("User not found");
            response.setStatusCode(404);
            return response;
        }
        response.setUser(existingUser.get());
        response.setMessage("User with id"+ userId);
        response.setStatusCode(200);
        return response;
    }

    @Override
    public UserDto updateUser(Users userDetails, Long id) {
        return null;
    }

    @Override
    public UserDto deleteById(Long id) {
        UserDto response =new UserDto();
        UserDto find =findById(id);
        if(find.getStatusCode() !=200){
            response.setMessage("User does not exist");
            response.setStatusCode(404);
            return response;
        }
        repository.deleteById(id);
        response.setMessage("User with id"+ id+" deleted");
        response.setStatusCode(204);
        return null;
    }

    @Override
    public UserDto findAllUsers() {
        UserDto response =new UserDto();
        var users =repository.findAll();
        response.setUsersList(users);
        response.setStatusCode(200);
        response.setMessage("All users");
        return response;
    }
}
