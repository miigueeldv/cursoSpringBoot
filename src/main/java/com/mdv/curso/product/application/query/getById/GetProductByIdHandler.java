package com.mdv.curso.product.application.query.getById;

import com.mdv.curso.mediator.RequestHandler;
import com.mdv.curso.product.domain.Product;
import com.mdv.curso.product.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetProductByIdHandler implements RequestHandler<GetProductByIdRequest, GetProductByIdResponse> {

    private final ProductRepository productRepository;

    @Override
    public GetProductByIdResponse handle(GetProductByIdRequest request) {


        Product product = productRepository.findById(request.getId()).orElseThrow(() -> new IllegalArgumentException("Product not found"));

        return new GetProductByIdResponse(product);
    }

    @Override
    public Class<GetProductByIdRequest> getRequestType() {
        return GetProductByIdRequest.class;
    }
}
