package com.mdv.curso.product.application.command.update;

import com.mdv.curso.mediator.RequestHandler;
import com.mdv.curso.product.domain.Product;
import com.mdv.curso.product.domain.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateProductHandler implements RequestHandler<UpdateProductRequest,Void> {

    private final ProductRepository productRepository;

    @Override
    public Void handle(UpdateProductRequest request) {
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
    public Class<UpdateProductRequest> getRequestType() {
        return UpdateProductRequest.class;
    }
}
