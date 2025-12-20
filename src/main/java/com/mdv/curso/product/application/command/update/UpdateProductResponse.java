package com.mdv.curso.product.application.command.update;

import com.mdv.curso.product.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UpdateProductResponse {

    private Product product;
    
}
