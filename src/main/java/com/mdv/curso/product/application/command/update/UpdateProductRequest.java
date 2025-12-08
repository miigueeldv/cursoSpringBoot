package com.mdv.curso.product.application.command.update;

import com.mdv.curso.mediator.Request;
import lombok.Data;

@Data
public class UpdateProductRequest implements Request<Void> {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String image;

}
