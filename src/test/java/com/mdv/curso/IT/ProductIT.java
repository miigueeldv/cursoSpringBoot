package com.mdv.curso.IT;

import com.mdv.curso.product.domain.entity.Product;
import com.mdv.curso.product.domain.port.ProductRepository;
import com.mdv.curso.product.infrastructure.api.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureTestRestTemplate
@Slf4j
@AutoConfigureMockMvc
public class ProductIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        log.info("Creating product");
        productRepository.upsert(
                Product.builder().id(1L).name("Product 1").description("Product 1 description").price(100.00).build());
    }

    @AfterEach
    public void tearDown() {
        productRepository.deleteById(1L);    }

    @Test
    public void getProductByIdExists(){
        ResponseEntity<ProductDto> response=restTemplate.getForEntity("/api/v1/products/1", ProductDto.class);

        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(1L,response.getBody().getId());
        Assertions.assertEquals("Product 1",response.getBody().getName());
        Assertions.assertEquals("Product 1 description",response.getBody().getDescription());
        Assertions.assertEquals(100.00,response.getBody().getPrice());
    }

    @Test
    public void saveProduct() throws Exception {

        MockMultipartFile file=new MockMultipartFile("file","image.jpeg","image/jpeg","test".getBytes());

        mockMvc.perform(multipart(HttpMethod.POST,"/api/v1/products/file")
                .file(file)
                .param("id","2")
                .param("name","Product 2")
                .param("description","Description 2")
                .param("price","150.00")
                .contentType(MediaType.MULTIPART_FORM_DATA)).andExpect(status().isCreated());
    }
}
