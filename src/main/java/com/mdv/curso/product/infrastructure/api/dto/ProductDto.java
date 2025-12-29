package com.mdv.curso.product.infrastructure.api.dto;

import com.mdv.curso.review.domain.Review;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String image;

    private String provider;

    private List<ReviewDto> reviews=new ArrayList<>();


}
