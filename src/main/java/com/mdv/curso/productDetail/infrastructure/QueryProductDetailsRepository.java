package com.mdv.curso.productDetail.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryProductDetailsRepository extends JpaRepository<ProductDetailEntity,Long> {
}
