package com.javanexus.spring_security_using_jwt.dtos;

import lombok.Builder;

@Builder
public record UserResponse(Long id,String userName,String role) {
}
