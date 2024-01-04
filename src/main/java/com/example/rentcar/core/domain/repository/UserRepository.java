package com.example.rentcar.core.domain.repository;

import com.example.rentcar.core.domain.User;
import com.example.rentcar.core.enums.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsernameAndRole(String username, Role role);

}
