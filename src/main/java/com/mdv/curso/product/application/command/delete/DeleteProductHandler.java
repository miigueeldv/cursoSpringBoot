package com.mdv.curso.product.application.command.delete;

import com.mdv.curso.common.application.mediator.RequestHandler;
import com.mdv.curso.product.domain.port.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteProductHandler implements RequestHandler<DeleteProductRequest,Void> {

    private final ProductRepository productRepository;

    @Override
    public Void handle(DeleteProductRequest request) {
        System.out.println("Eliminando producto con id "+request.getId()+" de forma asíncrona" );
        try {
            Thread.sleep(5000); //Para simular que la petición tarde 5s
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        productRepository.deleteById(request.getId());

        System.out.println("El producto con id "+request.getId()+" ha sido eliminado" );
        return null;
    }

    @Override
    public Class<DeleteProductRequest> getRequestType() {
        return DeleteProductRequest.class;
    }
}
