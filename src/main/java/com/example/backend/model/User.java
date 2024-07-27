package com.example.backend.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "user_info", schema = "api")
public class User {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", length = 60)
    private String name;

    @Column(name = "lastname", length = 60)
    private String lastname;

    @Column(name = "email", length = 60)
    private String email;

    @Column(name = "username", length = 60)
    private String username;

    @Column(name = "password", length = 60)
    private String password;

}
