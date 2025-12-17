package com.mdv.curso.product.infrastructure.api;

import com.mdv.curso.mediator.Mediator;
import com.mdv.curso.product.application.command.create.CreateProductRequest;
import com.mdv.curso.product.application.command.delete.DeleteProductRequest;
import com.mdv.curso.product.application.command.update.UpdateProductRequest;
import com.mdv.curso.product.application.query.getAll.GetAllProductRequest;
import com.mdv.curso.product.application.query.getAll.GetAllProductResponse;
import com.mdv.curso.product.application.query.getById.GetProductByIdRequest;
import com.mdv.curso.product.application.query.getById.GetProductByIdResponse;
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
import java.util.List;

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
    public ResponseEntity<List<ProductDto>> getAllProducts(@RequestParam(required = false) String pageSize) {
        log.info("Getting all products");
        GetAllProductResponse request=mediator.dispatch(new GetAllProductRequest());

        List<ProductDto> products=request.getProducts().stream().map(productMapper::mapToProduct).toList();
        log.info("Found {} products", products.size());

        return ResponseEntity.ok(products);
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
        log.info("Saving product with id: {}", productDto.getId());
        CreateProductRequest request = productMapper.mapToCreateProductRequest(productDto);

        mediator.dispatch(request);
        log.info("Saved product with id: {}", productDto.getId());
        return ResponseEntity.created(URI.create("/api/v1/products/".concat(productDto.getId().toString()))).build();
    }

    @Operation(summary = "Save product with file", description = "Save product with file")
    @PostMapping("/file")
    public ResponseEntity<Void> saveProductWithFile(@ModelAttribute @Valid CreateProductDto productDto) {
        log.info("Saving product with id: {} with file endpoint", productDto.getId());
        CreateProductRequest request = productMapper.mapToCreateProductRequest(productDto);

        mediator.dispatch(request);

        log.info("Saving product with id: {} with file endpoint", productDto.getId());
        return ResponseEntity.created(URI.create("/api/v1/products/".concat(productDto.getId().toString()))).build();
    }

    @Operation(summary = "Update product", description = "Update product")
    @PutMapping("")
    public ResponseEntity<Void> updateProduct(@RequestBody @Valid UpdateProductDto productDto) {
        log.info("Updating product with id: {}", productDto.getId());
        UpdateProductRequest request=productMapper.mapToCreateProductRequestUpdate(productDto);

        mediator.dispatch(request);

        log.info("Updated product with id: {}", productDto.getId());
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
