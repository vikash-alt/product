package com.product.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="product_table")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Integer pid;
    @Column(nullable = false)
    private String pName;
    @Column(nullable = false, unique = true)
    private String pCode;
    @Column(nullable = false)
    private String pType;
}
