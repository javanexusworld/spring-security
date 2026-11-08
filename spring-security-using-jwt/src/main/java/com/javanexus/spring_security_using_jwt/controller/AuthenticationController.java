package com.javanexus.spring_security_using_jwt.controller;


import com.javanexus.spring_security_using_jwt.dtos.AuthenticationRequest;
import com.javanexus.spring_security_using_jwt.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTUtil jwtUtil;

    @PostMapping("/login")
    public String getJwtToken(@RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.userName(), request.password()));
        return jwtUtil.genarateJWTToken(request.userName());
    }
}
