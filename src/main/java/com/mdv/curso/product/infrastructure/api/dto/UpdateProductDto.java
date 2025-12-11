package com.mdv.curso.product.infrastructure.api.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateProductDto {

    private Long id;

    @NotBlank(message = "Name cant be empty or null")
    private String name;

    @Length(min = 6, max = 255, message = "Description must be between 6 and 255 characters")
    private String description;

    @DecimalMin(value = "0.01")
    @DecimalMax(value = "9999.99", inclusive = false)
    private Double price;

    private MultipartFile file;


}
