package br.techfy.ecom.dto.response;

import br.techfy.ecom.model.Product;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record GetProductByIdResponse(
        UUID id,
        String name,
        String description,
        BigDecimal price,
        Integer stockQuantity,
        String sku,
        GetCategoryByIdResponse category,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static GetProductByIdResponse valueOf(Product product) {
        return GetProductByIdResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .sku(product.getSku())
                .category(GetCategoryByIdResponse.valueOf(product.getCategory()))
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}
