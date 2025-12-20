package com.mdv.curso.product.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "products")
@Data
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name = "description")
    @Length(max = 255)
    private String description;

    @Column(name="price")
    private Double price;

    @Column(name="image")
    private String image;

}
