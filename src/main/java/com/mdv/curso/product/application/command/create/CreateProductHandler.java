package com.mdv.curso.product.application.command.create;

import com.mdv.curso.common.application.mediator.RequestHandler;
import com.mdv.curso.product.domain.entity.Product;
import com.mdv.curso.product.domain.port.ProductRepository;
import com.mdv.curso.common.infrastructure.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProductHandler implements RequestHandler<CreateProductRequest, CreateProductResponse> {

    private final ProductRepository productRepository;
    private final FileUtils fileUtils;

    @Override
    public CreateProductResponse handle(CreateProductRequest request) {

        String uniqueFileName= fileUtils.saveProductImage(request.getFile());

        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .image(uniqueFileName)
                .build();

        return new CreateProductResponse(productRepository.upsert(product));
    }

    @Override
    public Class<CreateProductRequest> getRequestType() {
        return CreateProductRequest.class;
    }
}
