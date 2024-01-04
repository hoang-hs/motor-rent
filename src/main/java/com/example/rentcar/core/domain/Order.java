package com.example.rentcar.core.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Document(value = "order")
public class Order {
    @Id
    private String id;

    private Motor motor;

    private User user;

    private Integer number;

    @CreatedDate
    Instant createdAt;

    @LastModifiedDate
    Instant updatedAt;

    public Order() {
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }
}
