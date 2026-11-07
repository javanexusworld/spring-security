package com.javanexus.spring_security_basic_auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Mainclass {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String password = "User@123";
        String encodedPassword = encoder.encode(password);

        System.out.println("Username: admin");
        System.out.println("Password: " + password);
        System.out.println("BCrypt Hash: " + encodedPassword);
    }
}
