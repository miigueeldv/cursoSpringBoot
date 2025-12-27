package com.mdv.curso.productDetail.infrastructure;

import com.mdv.curso.product.infrastructure.database.entity.ProductEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_details")
public class ProductDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String specification;
    private String warranty;
    private String provider;

    @OneToOne(mappedBy = "productDetailEntity")
    private ProductEntity productEntity;
}
