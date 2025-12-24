package com.mdv.curso.product.application.query.getAll;

import com.mdv.curso.common.application.mediator.Request;
import com.mdv.curso.common.domain.PaginationQuery;
import com.mdv.curso.product.domain.entity.ProductFilter;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetAllProductRequest implements Request<GetAllProductResponse> {
    PaginationQuery paginationQuery;
    ProductFilter productFilter;
}
