package com.mdv.curso.product.infrastructure.api;

import com.mdv.curso.common.application.mediator.Mediator;
import com.mdv.curso.common.domain.PaginationQuery;
import com.mdv.curso.common.domain.PaginationResult;
import com.mdv.curso.product.application.command.create.CreateProductRequest;
import com.mdv.curso.product.application.command.create.CreateProductResponse;
import com.mdv.curso.product.application.command.delete.DeleteProductRequest;
import com.mdv.curso.product.application.command.update.UpdateProductRequest;
import com.mdv.curso.product.application.command.update.UpdateProductResponse;
import com.mdv.curso.product.application.query.getAll.GetAllProductRequest;
import com.mdv.curso.product.application.query.getAll.GetAllProductResponse;
import com.mdv.curso.product.application.query.getById.GetProductByIdRequest;
import com.mdv.curso.product.application.query.getById.GetProductByIdResponse;
import com.mdv.curso.product.domain.entity.Product;
import com.mdv.curso.product.domain.entity.ProductFilter;
import com.mdv.curso.product.infrastructure.api.dto.CreateProductDto;
import com.mdv.curso.product.infrastructure.api.dto.ProductDto;
import com.mdv.curso.product.infrastructure.api.dto.UpdateProductDto;
import com.mdv.curso.product.infrastructure.api.mapper.ProductMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
@Tag(name="Product", description = "Product API")
public class ProductController implements ProductApi {

    private final Mediator mediator;

    private final ProductMapper productMapper;

    @Operation(summary = "Get all products", description = "Get all products")
    @GetMapping("")
    public ResponseEntity<PaginationResult<ProductDto>> getAllProducts(@RequestParam(defaultValue = "0") int pageNumber,
                                                                       @RequestParam(defaultValue= "5") int pageSize,
                                                                       @RequestParam(defaultValue = "id") String sortBy,
                                                                       @RequestParam(defaultValue = "ASC") String direction,
                                                                       @RequestParam(required = false) String name,
                                                                       @RequestParam(required = false) String description,
                                                                       @RequestParam(required = false) Double priceMin,
                                                                       @RequestParam(required = false) Double priceMax){
        log.info("Getting all products");

        PaginationQuery paginationQuery=new PaginationQuery(pageNumber,pageSize,sortBy,direction);
        ProductFilter productFilter=new ProductFilter(name,description,priceMin,priceMax);
        GetAllProductRequest productRequest=new GetAllProductRequest(paginationQuery,productFilter);

        GetAllProductResponse response=mediator.dispatch(productRequest);

        PaginationResult<Product> productsPagination=response.getProductsPage();
        log.info("Found {} products", productsPagination.getTotalElements());

        PaginationResult<ProductDto> paginationDto=new PaginationResult<>(
                productsPagination.getContent().stream().map(productMapper::mapToProduct).toList(),
                productsPagination.getPage(),
                productsPagination.getSize(),
                productsPagination.getTotalPages(),
                productsPagination.getTotalElements()
        );

        return ResponseEntity.ok(paginationDto);
    }

    @Operation(summary = "Get product by id", description = "Get Product by id")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        log.info("Finding product by id: {}", id);
        GetProductByIdResponse response = mediator.dispatch(new GetProductByIdRequest(id));

        ProductDto productDto = productMapper.mapToProduct(response.getProduct());

        if (productDto == null) log.info("Product with id {} doesnt exist", id);

        return ResponseEntity.ok(productDto);
    }

    @Operation(summary = "Save product", description = "Save product")
    @PostMapping("")
    public ResponseEntity<Void> saveProduct(@RequestBody @Valid CreateProductDto productDto) {
        log.info("Saving product");
        CreateProductRequest request = productMapper.mapToCreateProductRequest(productDto);

        CreateProductResponse response=mediator.dispatch(request);

        Product productSaved=response.getProduct();

        log.info("Saved product with id: {}", productSaved.getId());

        return ResponseEntity.created(URI.create("/api/v1/products/".concat(productSaved.getId().toString()))).build();
    }

    @Operation(summary = "Save product with file", description = "Save product with file")
    @PostMapping("/file")
    public ResponseEntity<Void> saveProductWithFile(@ModelAttribute @Valid CreateProductDto productDto) {
        log.info("Saving product");
        CreateProductRequest request = productMapper.mapToCreateProductRequest(productDto);

        CreateProductResponse response=mediator.dispatch(request);

        Product productSaved=response.getProduct();

        log.info("Saving product with id: {} with file endpoint", productSaved.getId());
        return ResponseEntity.created(URI.create("/api/v1/products/".concat(productSaved.getId().toString()))).build();
    }

    @Operation(summary = "Update product", description = "Update product")
    @PutMapping("")
    public ResponseEntity<Void> updateProduct(@RequestBody @Valid UpdateProductDto productDto) {
        log.info("Updating product with id: {}", productDto.getId());
        UpdateProductRequest request=productMapper.mapToCreateProductRequestUpdate(productDto);

        UpdateProductResponse response=mediator.dispatch(request);

        log.info("Updated product with id: {}", response.getProduct().getId());
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update product with file", description = "Update product with file")
    @PutMapping("/file")
    public ResponseEntity<Void> updateProductWithFile(@ModelAttribute @Valid UpdateProductDto productDto) {
        log.info("Updating product with id: {} with file endpoint", productDto.getId());
        UpdateProductRequest request=productMapper.mapToCreateProductRequestUpdate(productDto);

        mediator.dispatch(request);

        log.info("Updated product with id: {} with file endpoint", productDto.getId());
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete product by id", description = "Delete product by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.info("Deleting product with id: {} with file endpoint", id);

        mediator.dispatchAsync(new DeleteProductRequest(id));

        log.info("Deleted product with id: {} with file endpoint", id);
        return ResponseEntity.accepted().build();
    }

}
