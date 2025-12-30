package com.mdv.curso.product.infrastructure.api.mapper;

import com.mdv.curso.category.domain.Category;
import com.mdv.curso.product.application.command.create.CreateProductRequest;
import com.mdv.curso.product.application.command.update.UpdateProductRequest;
import com.mdv.curso.product.domain.entity.Product;
import com.mdv.curso.product.infrastructure.api.dto.CreateProductDto;
import com.mdv.curso.product.infrastructure.api.dto.ProductDto;
import com.mdv.curso.product.infrastructure.api.dto.UpdateProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductMapper {

    CreateProductRequest mapToCreateProductRequest(CreateProductDto productDto);

    UpdateProductRequest mapToCreateProductRequestUpdate(UpdateProductDto productDto);

    @Mapping(target="provider",source = "productDetail.provider")
    ProductDto mapToProduct(Product product);

    default List<String> mapToCategoryNames(List<Category> categories) {
        return categories.stream().map(Category::getName).toList();
    }
}
