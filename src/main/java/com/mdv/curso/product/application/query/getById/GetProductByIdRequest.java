package com.mdv.curso.product.application.query.getById;

import com.mdv.curso.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetProductByIdRequest implements Request<GetProductByIdResponse> {

    private Long id;
}
