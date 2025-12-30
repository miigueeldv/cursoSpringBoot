package com.mdv.curso.product.infrastructure.database.entity;

import com.mdv.curso.category.infrastructure.CategoryEntity;
import com.mdv.curso.productDetail.infrastructure.ProductDetailEntity;
import com.mdv.curso.review.infrastructure.ReviewEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_detail_id")
    private ProductDetailEntity productDetailEntity;

    @OneToMany(mappedBy = "productEntity")
    private List<ReviewEntity> reviews = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "products_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<CategoryEntity> categories = new ArrayList<>();


}
