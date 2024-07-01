package com.archisacademy.jobportal.services.impl;

import com.archisacademy.jobportal.enums.UserRole;
import com.archisacademy.jobportal.loggers.MainLogger;
import com.archisacademy.jobportal.model.User;
import com.archisacademy.jobportal.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class UserCredentialsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final MainLogger LOGGER = new MainLogger(UserCredentialsServiceImpl.class);

    public UserCredentialsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByUsername(username);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapUserRoles(Collections.singleton(user.getUserRole())));
    }

    private Collection<? extends GrantedAuthority> mapUserRoles(Collection<UserRole> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
    }

    private User findUserByUsername(String username) {
        return userRepository.findByEmail(username).orElseThrow(() -> {
            LOGGER.log("User not fount with this email.", HttpStatus.NOT_FOUND);
            return null;
        });
    }
}
