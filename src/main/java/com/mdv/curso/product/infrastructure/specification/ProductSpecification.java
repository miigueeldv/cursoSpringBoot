package com.mdv.curso.product.infrastructure.specification;

import com.mdv.curso.product.infrastructure.database.entity.ProductEntity;
import org.springframework.data.jpa.domain.Specification;


public class ProductSpecification {

    public static Specification<ProductEntity> byName(String name) {
        return (root, query, cb) -> name == null ? null : cb.like(cb.upper(root.get("name")), "%"+name.toUpperCase()+"%");
    }

    public static Specification<ProductEntity> byDescription(String description) {
        return (root, query, cb) -> description == null ? null : cb.like(cb.upper(root.get("description")), "%"+description.toUpperCase()+"%");
    }

    public static Specification<ProductEntity> byPriceBetween(Double priceMin,Double priceMax) {
        return (root, query, cb) -> (priceMin == null || priceMax == null) ? null : cb.between(root.get("price"), priceMin,priceMax);
    }

}
