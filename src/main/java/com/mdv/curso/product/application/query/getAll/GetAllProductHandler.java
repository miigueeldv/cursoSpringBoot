package com.mdv.curso.product.application.query.getAll;

import com.mdv.curso.common.application.mediator.RequestHandler;
import com.mdv.curso.common.domain.PaginationResult;
import com.mdv.curso.product.domain.entity.Product;
import com.mdv.curso.product.domain.port.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllProductHandler implements RequestHandler<GetAllProductRequest, GetAllProductResponse> {

    private final ProductRepository productRepository;

    @Override
    public GetAllProductResponse handle(GetAllProductRequest request) {

        PaginationResult<Product> products=productRepository.findAll(request.getPaginationQuery(),request.getProductFilter());
        return new GetAllProductResponse(products);
    }

    @Override
    public Class<GetAllProductRequest> getRequestType() {
        return GetAllProductRequest.class;
    }
}
