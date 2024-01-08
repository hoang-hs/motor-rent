package com.example.rentcar.core.domain.repository;

import com.example.rentcar.core.domain.Motor;
import com.example.rentcar.core.domain.Order;
import com.example.rentcar.core.enums.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findAllByStatusAndMotorAndUpdatedAtBetween(Status status, Motor motor, Date fromDate, Date toDate);
}
