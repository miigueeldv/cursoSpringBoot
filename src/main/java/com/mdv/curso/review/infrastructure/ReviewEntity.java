package com.mdv.curso.review.infrastructure;

import com.mdv.curso.product.infrastructure.database.entity.ProductEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="reviews")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    private Integer score;

    @ManyToOne
    @JoinColumn(name="product_id")
    private ProductEntity productEntity;

}