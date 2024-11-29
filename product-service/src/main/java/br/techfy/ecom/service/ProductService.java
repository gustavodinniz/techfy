package br.techfy.ecom.service;

import br.techfy.ecom.dto.request.CreateProductRequest;
import br.techfy.ecom.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
