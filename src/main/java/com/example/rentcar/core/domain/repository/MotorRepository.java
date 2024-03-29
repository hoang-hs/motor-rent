package com.example.rentcar.core.domain.repository;

import com.example.rentcar.core.domain.Motor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MotorRepository extends MongoRepository<Motor, String> {
    Optional<Motor> findByType(String type);
}
