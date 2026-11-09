package com.javanexus.spring_security_using_jwt.service;


import com.javanexus.spring_security_using_jwt.dtos.UserRegisterRequest;
import com.javanexus.spring_security_using_jwt.dtos.UserResponse;
import com.javanexus.spring_security_using_jwt.entities.Role;
import com.javanexus.spring_security_using_jwt.entities.Users;
import com.javanexus.spring_security_using_jwt.exception.UserAlreadyRegisteredException;
import com.javanexus.spring_security_using_jwt.repos.UserRepo;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    public UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

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

    public UserResponse registerUser(UserRegisterRequest registerUser) {
        if(findByUserName(registerUser.userName()).isEmpty()){
            Users users= new Users();
            users.setUserName(registerUser.userName());
            users.setRole(Role.valueOf(registerUser.role()));
            users.setPassword(passwordEncoder.encode(registerUser.password()));
            Users savedUser = userRepo.save(users);
            UserResponse userResponse=UserResponse.builder()
                    .id(savedUser.getId())
                    .userName(savedUser.getUserName())
                    .role(savedUser.getRole().name())
                    .build();
            return userResponse;
        }else{
            throw new UserAlreadyRegisteredException("User already exists");
        }
    }
}
