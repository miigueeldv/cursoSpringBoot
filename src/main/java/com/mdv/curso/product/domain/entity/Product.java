package com.mdv.curso.product.domain.entity;

import com.mdv.curso.productDetail.domain.ProductDetail;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Product {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String image;

    private ProductDetail productDetail;

}
