package com.mdv.curso.product.application.command.delete;

import com.mdv.curso.mediator.RequestHandler;
import com.mdv.curso.product.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteProductHandler implements RequestHandler<DeleteProductRequest,Void> {

    private final ProductRepository productRepository;

    @Override
    public Void handle(DeleteProductRequest request) {
        productRepository.deleteById(request.getId());
        return null;
    }

    @Override
    public Class<DeleteProductRequest> getRequestType() {
        return DeleteProductRequest.class;
    }
}
