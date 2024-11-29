package br.techfy.ecom.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

public record UpdateProductRequest(
        String name,
        String description,
        BigDecimal price,
        Integer stockQuantity,
        String sku,
        UUID categoryId
) {
}
