package com.mdv.curso.product.infrastructure.api;

import com.mdv.curso.mediator.Mediator;
import com.mdv.curso.product.application.query.getAll.GetAllProductRequest;
import com.mdv.curso.product.application.query.getAll.GetAllProductResponse;
import com.mdv.curso.product.domain.entity.Product;
import com.mdv.curso.product.infrastructure.api.dto.ProductDto;
import com.mdv.curso.product.infrastructure.api.mapper.ProductMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private Mediator mediator;

    @Mock
    private ProductMapper mapper;

    @InjectMocks
    private ProductController productController;

    @Test
    @DisplayName("getAllProducts --> 16/12/2025")
    public void getAllProducts(){
        GetAllProductResponse responseGetAll = new GetAllProductResponse(
                List.of(Product.builder().id(1L).description("Producto Test").build(),
                        Product.builder().id(2L).description("Producto Test 2").build())
                );

        Mockito.when(mediator.dispatch(new GetAllProductRequest())).thenReturn(responseGetAll);

        ProductDto productDto=new ProductDto();
        productDto.setId(99L);
        Mockito.when(mapper.mapToProduct(any(Product.class))).thenReturn(productDto);


        ResponseEntity<List<ProductDto>> response = productController.getAllProducts("5");

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertNotNull(response.getBody());

        List<ProductDto> productsDto=response.getBody();
        Assertions.assertEquals(2,productsDto.size());

    }

}