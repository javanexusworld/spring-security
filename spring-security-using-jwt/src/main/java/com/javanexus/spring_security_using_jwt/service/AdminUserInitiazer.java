package com.javanexus.spring_security_using_jwt.service;

import com.javanexus.spring_security_using_jwt.entities.Role;
import com.javanexus.spring_security_using_jwt.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitiazer {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner createAdminUser(){
        return  args -> {
            if(userService.findByUserName("admin").isEmpty()){
                Users admin = new Users();
                admin.setUserName("admin");
                admin.setPassword(passwordEncoder.encode("Admin@123")); // Securely store password
                admin.setRole(Role.ADMIN);
                userService.save(admin);
                System.out.println("Default admin user created!");
            }
            if (userService.findByUserName("user").isEmpty()) {
                Users admin = new Users();
                admin.setUserName("user");
                admin.setPassword(passwordEncoder.encode("User@1234")); // Securely store password
                admin.setRole(Role.USER);
                userService.save(admin);
                System.out.println("Default admin user created!");
            }
        };
    }
}
