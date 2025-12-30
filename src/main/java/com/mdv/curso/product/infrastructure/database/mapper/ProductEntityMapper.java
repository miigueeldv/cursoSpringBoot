package com.mdv.curso.product.infrastructure.database.mapper;

import com.mdv.curso.category.domain.Category;
import com.mdv.curso.category.infrastructure.CategoryEntity;
import com.mdv.curso.product.domain.entity.Product;
import com.mdv.curso.product.infrastructure.database.entity.ProductEntity;
import com.mdv.curso.review.domain.Review;
import com.mdv.curso.review.infrastructure.ReviewEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductEntityMapper {

    @Mapping(target="productDetailEntity", source = "productDetail")
    @Mapping(target="productDetailEntity.productEntity", ignore = true)
    ProductEntity mapToProductEntity(Product product);

    @Mapping(target="productDetail", source = "productDetailEntity")
    @Mapping(target="productDetail.product", ignore = true)
    Product mapToProduct(ProductEntity productEntity);

    @Mapping(target = "product", ignore = true)
    Review mapToReview(ReviewEntity reviewEntity);

    @Mapping(target = "productEntity", ignore = true)
    ReviewEntity mapToReviewEntity(Review review);

    @Mapping(target = "products", ignore = true)
    Category mapToReview(CategoryEntity categoryEntity);

    @Mapping(target = "products", ignore = true)
    CategoryEntity mapToReviewEntity(Category category);
}