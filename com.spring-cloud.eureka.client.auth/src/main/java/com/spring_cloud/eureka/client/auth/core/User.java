package com.spring_cloud.eureka.client.auth.core;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String userId;
    private String username;
    private String password;
    private String role;
}
