package com.javanexus.spring_security_using_jwt.controller;

import com.javanexus.spring_security_using_jwt.dtos.UserRegisterRequest;
import com.javanexus.spring_security_using_jwt.dtos.UserResponse;
import com.javanexus.spring_security_using_jwt.entities.Role;
import com.javanexus.spring_security_using_jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController {


    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRegisterRequest request){
        UserRegisterRequest registerUser = UserRegisterRequest.builder()
                .role(Role.USER.name())
                .userName(request.userName())
                .password(request.password()).build();
     return ResponseEntity.ok(userService.registerUser(registerUser));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/register")
    public ResponseEntity<UserResponse> registerWithAdmin(@RequestBody UserRegisterRequest request){
        return ResponseEntity.ok(userService.registerUser(request));
    }
}
