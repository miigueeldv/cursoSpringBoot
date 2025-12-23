package com.mdv.curso.product.application.query.getById;

import com.mdv.curso.common.application.mediator.RequestHandler;
import com.mdv.curso.product.domain.entity.Product;
import com.mdv.curso.product.domain.exception.ProductNotFoundException;
import com.mdv.curso.product.domain.port.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetProductByIdHandler implements RequestHandler<GetProductByIdRequest, GetProductByIdResponse> {

    private final ProductRepository productRepository;

    @Override
    public GetProductByIdResponse handle(GetProductByIdRequest request) {
        Product product = productRepository.findById(request.getId()).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        return new GetProductByIdResponse(product);
    }

    @Override
    public Class<GetProductByIdRequest> getRequestType() {
        return GetProductByIdRequest.class;
    }
}
