package com.mdv.curso.product.application.command.update;

import com.mdv.curso.category.domain.Category;
import com.mdv.curso.category.infrastructure.CategoryEntityMapper;
import com.mdv.curso.category.infrastructure.QueryCategoryRepository;
import com.mdv.curso.common.application.mediator.RequestHandler;
import com.mdv.curso.product.domain.entity.Product;
import com.mdv.curso.product.domain.exception.ProductNotFoundException;
import com.mdv.curso.product.domain.port.ProductRepository;
import com.mdv.curso.common.infrastructure.utils.FileUtils;
import com.mdv.curso.productDetail.domain.ProductDetail;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class UpdateProductHandler implements RequestHandler<UpdateProductRequest,UpdateProductResponse> {

    private final ProductRepository productRepository;
    private final FileUtils fileUtils;
    private final QueryCategoryRepository queryCategoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Override
    public UpdateProductResponse handle(UpdateProductRequest request) {

        String uniqueFileName= fileUtils.saveProductImage(request.getFile());

        Product product= productRepository.findById(request.getId()).orElseThrow(() -> new ProductNotFoundException(request.getId().toString()));

        ProductDetail productDetail=product.getProductDetail();
        productDetail.setProvider(request.getProvider());

        product.getReviews().add(request.getReview());
        Category category=queryCategoryRepository.findById(request.getCategoryId()).map(categoryEntityMapper::mapToCategory).orElseThrow(() -> new RuntimeException("Category not found"));
        product.getCategories().add(category);
        UpdateProductResponse response= new UpdateProductResponse(productRepository.upsert(product));

        if (product.getId() == 8){throw new RuntimeException("Test transactional anotation when updating product");}

        return response;
    }

    @Override
    public Class<UpdateProductRequest> getRequestType() {
        return UpdateProductRequest.class;
    }
}
