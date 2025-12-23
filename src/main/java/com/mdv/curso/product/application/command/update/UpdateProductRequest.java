package com.mdv.curso.product.application.command.update;

import com.mdv.curso.common.application.mediator.Request;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateProductRequest implements Request<UpdateProductResponse> {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private MultipartFile file;

}
