package br.techfy.ecom.dto.response;

import br.techfy.ecom.model.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record UpdateProductResponse(
        UUID id,
        String name,
        String description,
        BigDecimal price,
        Integer stockQuantity,
        String sku,
        UpdateProductCategoryResponse category,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static UpdateProductResponse valueOf(Product product) {
        return new UpdateProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getSku(),
                UpdateProductCategoryResponse.valueOf(product.getCategory()),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}
