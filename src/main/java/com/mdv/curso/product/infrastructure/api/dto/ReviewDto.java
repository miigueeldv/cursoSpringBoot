package com.mdv.curso.product.infrastructure.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

    private Long id;
    private String comment;
    private Integer score;

}
