package com.mdv.curso.product.application.command.create;

import com.mdv.curso.mediator.Request;
import lombok.Data;

@Data
public class CreateProductRequest implements Request<Void> {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String image;
}
