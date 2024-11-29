package br.techfy.ecom.service;

import br.techfy.ecom.dto.request.CreateProductRequest;
import br.techfy.ecom.dto.request.UpdateProductRequest;
import br.techfy.ecom.dto.response.GetProductByIdResponse;
import br.techfy.ecom.dto.response.UpdateProductResponse;
import br.techfy.ecom.exception.CategoryNotFoundException;
import br.techfy.ecom.exception.ProductNotFoundException;
import br.techfy.ecom.model.Category;
import br.techfy.ecom.model.Product;
import br.techfy.ecom.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final CategoryService categoryService;
    private final ProductRepository productRepository;

    public String createProduct(CreateProductRequest createProductRequest) {
        log.info("Starting operation to create product: {}", createProductRequest.name());
        var category = categoryService.findCategoryById(createProductRequest.categoryId());
        var product = createProductRequest.toProduct();
        product.setCategory(category);
        productRepository.save(product);
        log.info("Product created with ID: {}", product.getId());
        return product.getId().toString();
    }

    public GetProductByIdResponse getProductById(UUID id) {
        log.info("Starting operation to get product by ID: {}", id);
        var product = findProductById(id);
        log.info("Product found for ID: {}", id);
        return GetProductByIdResponse.valueOf(product);
    }

    private Product findProductById(UUID id) {
        log.info("Find product by ID: {}", id);
        return productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product not found for ID: {}", id);
                    return new ProductNotFoundException("404.002");
                });
    }

    public void deleteProduct(UUID id) {
        log.info("Starting operation to delete product by ID: {}", id);
        productRepository.deleteById(id);
        log.info("Product deleted for ID: {}", id);
    }

    public UpdateProductResponse updateProduct(UUID id, UpdateProductRequest updateProductRequest) {
        log.info("Starting operation to update product by ID: {}", id);
        var category = verifyCategory(updateProductRequest);
        var product = findProductById(id);
        product.setName(Optional.ofNullable(updateProductRequest.name()).orElse(product.getName()));
        product.setDescription(Optional.ofNullable(updateProductRequest.description()).orElse(product.getDescription()));
        product.setPrice(Optional.ofNullable(updateProductRequest.price()).orElse(product.getPrice()));
        product.setStockQuantity(Optional.ofNullable(updateProductRequest.stockQuantity()).orElse(product.getStockQuantity()));
        product.setSku(Optional.ofNullable(updateProductRequest.sku()).orElse(product.getSku()));
        product.setCategory(Optional.ofNullable(category).orElse(product.getCategory()));
        productRepository.save(product);
        log.info("Product updated for ID: {}", id);
        return UpdateProductResponse.valueOf(product);
    }

    private Category verifyCategory(UpdateProductRequest updateProductRequest) {
        if (updateProductRequest.categoryId() == null) {
            return null;
        }

        try {
            return categoryService.findCategoryById(updateProductRequest.categoryId());
        } catch (CategoryNotFoundException e) {
            log.warn("Category not found for ID: {}", updateProductRequest.categoryId());
            return null;
        }
    }
}
