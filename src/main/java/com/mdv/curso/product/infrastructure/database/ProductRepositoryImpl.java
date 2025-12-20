package com.mdv.curso.product.infrastructure.database;

import com.mdv.curso.product.domain.entity.Product;
import com.mdv.curso.product.domain.port.ProductRepository;
import com.mdv.curso.product.infrastructure.database.entity.ProductEntity;
import com.mdv.curso.product.infrastructure.database.mapper.ProductEntityMapper;
import com.mdv.curso.product.infrastructure.database.repository.QueryProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final QueryProductRepository repository;
    private final ProductEntityMapper productEntityMapper;


    @Override
    public Product upsert(Product product) {
        ProductEntity productEntity = productEntityMapper.mapToProductEntity(product);
        ProductEntity productSaved = repository.save(productEntity);
        return productEntityMapper.mapToProduct(productSaved);
    }

    @Override
    public Optional<Product> findById(Long id) {return repository.findById(id).map(productEntityMapper::mapToProduct);}

    @Override
    public List<Product> findAll() {return repository.findAll().stream().map(productEntityMapper::mapToProduct).toList();}

    @Override
    public void deleteById(Long id) {repository.deleteById(id);}
}
