package com.mdv.curso.product.application.query.getById;

import com.mdv.curso.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GetProductByIdResponse {

    private Product product;
}

