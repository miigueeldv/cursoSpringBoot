package com.mdv.curso.product.infrastructure.database;

import com.mdv.curso.common.domain.PaginationQuery;
import com.mdv.curso.common.domain.PaginationResult;
import com.mdv.curso.product.domain.entity.Product;
import com.mdv.curso.product.domain.port.ProductRepository;
import com.mdv.curso.product.infrastructure.database.entity.ProductEntity;
import com.mdv.curso.product.infrastructure.database.mapper.ProductEntityMapper;
import com.mdv.curso.product.infrastructure.database.repository.QueryProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

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
    public PaginationResult<Product> findAll(PaginationQuery paginationQuery) {
        PageRequest pageRequest = PageRequest.of(
                paginationQuery.getPage(),
                paginationQuery.getSize(),
                Sort.Direction.fromString(paginationQuery.getDirection()),
                paginationQuery.getSortBy()
        );

        Page<ProductEntity> page=repository.findAll(pageRequest);

        return new PaginationResult<>(
                    page.getContent().stream().map(productEntityMapper::mapToProduct).toList(),
                    page.getNumber(),
                    page.getSize(),
                    page.getTotalPages(),
                    (int)page.getTotalElements()
        );
    }

    @Override
    public void deleteById(Long id) {repository.deleteById(id);}
}
