package com.example.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "product", schema = "api")
public class Product {

    @Id
    @Column(name = "id", length = 36)
    private UUID id;

    @Column(name = "name", length = 60)
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "category", length = 60)
    private String category;

    @Column(name = "imageurl", length = 400)
    private String imageUrl;

    @Column(name = "description", length = 2000)
    private String description;


}
