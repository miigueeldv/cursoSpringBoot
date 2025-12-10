package com.mdv.curso.product.infrastructure.api.mapper;

import com.mdv.curso.product.application.command.create.CreateProductRequest;
import com.mdv.curso.product.application.command.update.UpdateProductRequest;
import com.mdv.curso.product.domain.entity.Product;
import com.mdv.curso.product.infrastructure.api.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductMapper {

    CreateProductRequest mapToCreateProductRequest(ProductDto productDto);

    UpdateProductRequest mapToCreateProductRequestUpdate(ProductDto productDto);

    ProductDto mapToProduct(Product product);

}
