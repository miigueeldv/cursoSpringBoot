package com.mdv.curso.product.application.query.getAll;

import com.mdv.curso.mediator.Request;
import com.mdv.curso.product.application.query.getById.GetProductByIdResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetAllProductRequest implements Request<GetAllProductResponse> {}
