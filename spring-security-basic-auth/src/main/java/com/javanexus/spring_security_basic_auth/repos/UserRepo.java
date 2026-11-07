package com.javanexus.spring_security_basic_auth.repos;

import com.javanexus.spring_security_basic_auth.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {

    public Optional<Users> findByUserName(String userName);
}
