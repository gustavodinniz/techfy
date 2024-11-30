package br.techfy.ecom.dto.response;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder
public record AddProductToCartResponse(
        UUID cartId,
        BigDecimal totalPrice,
        List<ProductResponse> products
) {
}
