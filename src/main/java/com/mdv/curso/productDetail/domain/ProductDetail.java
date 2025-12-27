package com.mdv.curso.productDetail.domain;

import com.mdv.curso.product.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProductDetail {

    private Long id;
    private String specification;
    private String warranty;
    private String provider;

    private Product product;

}
