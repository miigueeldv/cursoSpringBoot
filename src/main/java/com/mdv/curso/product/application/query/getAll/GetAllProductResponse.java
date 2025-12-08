package com.mdv.curso.product.application.query.getAll;

import com.mdv.curso.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class GetAllProductResponse {

    private List<Product> products;
}

