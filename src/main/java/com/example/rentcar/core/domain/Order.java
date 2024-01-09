package com.example.rentcar.core.domain;

import com.example.rentcar.core.enums.Status;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Date;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Document(value = "order")
public class Order {
    @Id
    private String id;

    private Motor motor;

    private User user;

    private Integer number;

    private Status status;

    private Date dateOrder;

    @CreatedDate
    Date createdAt;

    @LastModifiedDate
    Date updatedAt;

    public Order(User user, Motor motor, Integer number, Date dateOrder) {
        this.user = user;
        this.motor = motor;
        this.number = number;
        this.dateOrder = dateOrder;
        this.status = Status.NEW;
        this.createdAt = Date.from(Instant.now());
        this.updatedAt = Date.from(Instant.now());
    }
}
