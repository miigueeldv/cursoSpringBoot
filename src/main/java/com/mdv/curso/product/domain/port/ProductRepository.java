package com.mdv.curso.product.domain.port;

import com.mdv.curso.common.domain.PaginationQuery;
import com.mdv.curso.common.domain.PaginationResult;
import com.mdv.curso.product.domain.entity.Product;
import com.mdv.curso.product.domain.entity.ProductFilter;
import com.mdv.curso.product.infrastructure.database.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Product upsert(Product product);

    Optional<Product> findById(Long id);

    PaginationResult<Product> findAll(PaginationQuery paginationQuery, ProductFilter productFilter);

    void deleteById(Long id);
}
