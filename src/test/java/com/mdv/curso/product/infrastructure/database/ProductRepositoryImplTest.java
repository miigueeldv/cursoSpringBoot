package com.mdv.curso.product.infrastructure.database;

import com.mdv.curso.product.domain.entity.Product;
import com.mdv.curso.product.domain.port.ProductRepository;
import com.mdv.curso.product.infrastructure.database.entity.ProductEntity;
import com.mdv.curso.product.infrastructure.database.mapper.ProductEntityMapper;
import com.mdv.curso.product.infrastructure.database.repository.QueryProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryImplTest {

    @Mock
    private QueryProductRepository repository;

    @Mock
    private ProductEntityMapper mapper;

    @InjectMocks
    private ProductRepositoryImpl productRepository;

    @Test
    void shouldNotReturnProductWhenFound(){
        Optional<Product> optionalProduct=productRepository.findById(1L);
        assertTrue(optionalProduct.isEmpty());
    }

    @Test
    void shouldReturnProductWhenFound(){
        when(repository.findById(1L)).thenReturn(Optional.of(new ProductEntity()));
        when(mapper.mapToProduct(ArgumentMatchers.any(ProductEntity.class))).thenReturn(Product.builder().id(1L).build());
        Optional<Product> optionalProduct=productRepository.findById(1L);
        assertTrue(optionalProduct.isPresent());
    }
}