package com.javanexus.spring_security_using_jwt.dtos;

import lombok.Builder;

@Builder
public record UserRegisterRequest(String userName,String password,String role) {

}
