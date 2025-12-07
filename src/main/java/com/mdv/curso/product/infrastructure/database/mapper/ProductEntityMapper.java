package com.mdv.curso.product.infrastructure.database.mapper;

import com.mdv.curso.product.domain.Product;
import com.mdv.curso.product.infrastructure.database.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductEntityMapper {

    ProductEntity mapToProductEntity(Product product);

    Product mapToProduct(ProductEntity productEntity);

}
