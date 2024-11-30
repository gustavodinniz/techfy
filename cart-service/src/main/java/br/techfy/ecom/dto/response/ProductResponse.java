package br.techfy.ecom.dto.response;

import br.techfy.ecom.dto.GetProductByIdDTO;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record ProductResponse(
        UUID productId,
        String productName,
        String description,
        Integer quantity,
        BigDecimal price,
        BigDecimal discount,
        BigDecimal specialPrice
) {

    public static ProductResponse valueOf(GetProductByIdDTO product) {
        return ProductResponse.builder()
                .productId(product.getId())
                .productName(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .discount(BigDecimal.ZERO)
                .specialPrice(product.getPrice())
                .build();
    }
}
