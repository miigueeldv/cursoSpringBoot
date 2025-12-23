package com.mdv.curso.product.application.command.delete;

import com.mdv.curso.common.application.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteProductRequest implements Request<Void> {
    private Long id;
}
