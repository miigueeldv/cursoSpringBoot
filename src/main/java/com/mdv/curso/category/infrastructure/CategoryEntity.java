package com.mdv.curso.category.infrastructure;

import com.mdv.curso.product.infrastructure.database.entity.ProductEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<ProductEntity> products = new ArrayList<>();;

}
