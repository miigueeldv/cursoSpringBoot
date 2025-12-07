package com.mdv.curso.product.application.command.create;

import com.mdv.curso.mediator.RequestHandler;
import com.mdv.curso.product.domain.Product;
import com.mdv.curso.product.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProductHandler implements RequestHandler<CreateProductRequest, Void> {

    private final ProductRepository productRepository;

    @Override
    public Void handle(CreateProductRequest request) {

        Product product = Product.builder()
                .id(request.getId())
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .image(request.getImage())
                .build();

        productRepository.upsert(product);

        return null;
    }

    @Override
    public Class<CreateProductRequest> getRequestType() {
        return CreateProductRequest.class;
    }
}
