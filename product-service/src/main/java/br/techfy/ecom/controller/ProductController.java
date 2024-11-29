package br.techfy.ecom.controller;

import br.techfy.ecom.dto.request.CreateProductRequest;
import br.techfy.ecom.dto.request.UpdateProductRequest;
import br.techfy.ecom.dto.response.GetProductByIdResponse;
import br.techfy.ecom.dto.response.GetProductsResponse;
import br.techfy.ecom.dto.response.UpdateProductResponse;
import br.techfy.ecom.service.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody @Valid CreateProductRequest createProductRequest, HttpServletResponse response) {
        var uuid = productService.createProduct(createProductRequest);
        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(uuid)
                .toUri();
        response.addHeader("Location", location.toString());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetProductByIdResponse getProductById(@PathVariable UUID id) {
        return productService.getProductById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdateProductResponse updateProduct(@PathVariable UUID id, @RequestBody UpdateProductRequest updateProductRequest) {
        return productService.updateProduct(id, updateProductRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetProductsResponse> getProductsByFilters(@RequestParam(value = "name", required = false) String name,
                                                          @RequestParam(value = "category", required = false) String category,
                                                          @RequestParam(value = "min_price", required = false) BigDecimal minPrice,
                                                          @RequestParam(value = "max_price", required = false) BigDecimal maxPrice) {
        return productService.getProductsByFilters(name, category, minPrice, maxPrice);
    }
}
