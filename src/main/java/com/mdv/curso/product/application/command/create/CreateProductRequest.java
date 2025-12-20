package com.mdv.curso.product.application.command.create;

import com.mdv.curso.mediator.Request;
import com.mdv.curso.product.domain.entity.Product;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateProductRequest implements Request<CreateProductResponse> {

    private String name;
    private String description;
    private Double price;
    private MultipartFile file;
}
