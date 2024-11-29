package br.techfy.ecom.dto.response;

import br.techfy.ecom.model.Product;

import java.math.BigDecimal;
import java.util.UUID;

public record GetProductsResponse(
        UUID id,
        String name,
        String description,
        BigDecimal price,
        Integer stockQuantity,
        String sku,
        String category
) {

    public static GetProductsResponse valueOf(Product product) {
        return new GetProductsResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getSku(),
                product.getCategory().getName()
        );
    }
}
