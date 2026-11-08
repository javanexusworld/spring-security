package com.javanexus.spring_security_using_jwt.repos;


import com.javanexus.spring_security_using_jwt.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {

    Optional<Users> findByUserName(String userName);
}
