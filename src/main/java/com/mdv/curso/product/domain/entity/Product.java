package com.mdv.curso.product.domain.entity;

import com.mdv.curso.category.domain.Category;
import com.mdv.curso.productDetail.domain.ProductDetail;
import com.mdv.curso.review.domain.Review;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class Product {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String image;

    private ProductDetail productDetail;
    private List<Review> reviews = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();

}
