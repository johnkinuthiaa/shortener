package com.slippery.shortener.service;

import com.slippery.shortener.models.UserPrincipal;
import com.slippery.shortener.models.Users;
import com.slippery.shortener.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users =userRepository.findByUsername(username);
        if(users ==null){
            throw new UsernameNotFoundException("User not found");
        }

        return new UserPrincipal(users);
    }
}
