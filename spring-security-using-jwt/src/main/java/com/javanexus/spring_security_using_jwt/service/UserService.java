package com.javanexus.spring_security_using_jwt.service;


import com.javanexus.spring_security_using_jwt.entities.Users;
import com.javanexus.spring_security_using_jwt.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    public UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepo.findByUserName(userName).
                orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    public Optional<Users> findByUserName(String userName){
        return userRepo.findByUserName(userName);
    }

    public void save(Users admin) {
        userRepo.save(admin);
    }
}
