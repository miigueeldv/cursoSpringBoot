package com.mdv.curso.review.domain;

import com.mdv.curso.product.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Review {

    private Long id;
    private String comment;
    private Integer score;

    private Product product;
}
