package com.itp.MyFirstSpringBootApp.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;

    @NotBlank(message = "Product title is required")
    @Size(min = 3, max = 50, message = "Product title must be between 3 and 50 characters")
    private String productTitle;

    @NotBlank(message = "Product description cannot be empty")
    @Size(min = 5, max = 200, message = "Product description must be between 5 and 200 characters")
    private String productDesc;

    @NotBlank(message = "Product category is required")
    private String productCategory;

    @Positive(message = "Price must be greater than 0")
    private double price;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @PrePersist
    protected void atCreation() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.modifiedAt = now;
    }

    @PreUpdate
    protected void atUpdation() {
        this.modifiedAt = LocalDateTime.now();
    }
}
