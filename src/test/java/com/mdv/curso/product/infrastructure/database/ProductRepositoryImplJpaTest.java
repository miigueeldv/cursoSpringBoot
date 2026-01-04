package com.mdv.curso.product.infrastructure.database;


import com.mdv.curso.product.infrastructure.database.entity.ProductEntity;
import com.mdv.curso.product.infrastructure.database.repository.QueryProductRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryImplJpaTest {

    @Autowired
    private QueryProductRepository repository;

    @Test
    void shouldNotReturnProductWhenFound(){
        Optional<ProductEntity> optionalProduct=repository.findById(1L);
        assertTrue(optionalProduct.isEmpty());
    }

    @Test
    void shouldReturnProductWhenFound(){
        Optional<ProductEntity> optionalProduct=repository.findById(9L);
        assertTrue(optionalProduct.isPresent());
    }
}