package com.example.rentcar.core.domain;

import com.example.rentcar.core.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Date;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Document(value = "user")
@Builder
public class User {
    @Id
    private String id;

    @Indexed(unique = true)
    private String username;
    @JsonIgnore
    private String password;
    private Role role;

    private String name;
    private String email;
    private String number;

    @CreatedDate
    @Builder.Default
    Date createdAt = Date.from(Instant.now());

    @LastModifiedDate
    @Builder.Default
    Date updatedAt = Date.from(Instant.now());
}
