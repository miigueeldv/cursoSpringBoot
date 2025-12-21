package com.mdv.curso.product.infrastructure.database.repository;

import com.mdv.curso.product.infrastructure.database.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QueryProductRepository extends JpaRepository<ProductEntity, Long> {


    Optional<ProductEntity> findByNameContainingIgnoreCase(String name);

    List<ProductEntity> findAllByPriceBetween(Double min, Double max);

    @Query("select p " +
            "from ProductEntity p " +
            "where p.name like concat('%', ?1, '%') " +
            "and p.description like concat('%', ?2, '%') " +
            "or p.price between ?3 and ?4")
    List<ProductEntity> findProductsDetails(String name, String description,Double min, Double max);

    boolean existsByName(String name);

    long countByPrice(Double price);

}

