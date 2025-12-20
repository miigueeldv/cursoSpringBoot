package com.mdv.curso.product.application.command.create;

import com.mdv.curso.product.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateProductResponse {

    private Product product;

}
