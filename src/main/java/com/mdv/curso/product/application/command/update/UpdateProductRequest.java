package com.mdv.curso.product.application.command.update;

import com.mdv.curso.common.application.mediator.Request;
import com.mdv.curso.product.infrastructure.api.dto.ReviewDto;
import com.mdv.curso.review.domain.Review;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateProductRequest implements Request<UpdateProductResponse> {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private MultipartFile file;
    private String provider;
    private Review review;
    private Long categoryId;

}
