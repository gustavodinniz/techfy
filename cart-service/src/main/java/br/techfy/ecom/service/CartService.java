package br.techfy.ecom.service;

import br.techfy.ecom.dto.response.AddProductToCartResponse;
import br.techfy.ecom.dto.response.ProductResponse;
import br.techfy.ecom.model.Cart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductService productService;

    public AddProductToCartResponse addProductToCart(UUID productId, Integer quantity) {
        var cart = buildNewCart();
        var product = productService.getProductById(productId);
        ProductResponse.valueOf(product);
        return AddProductToCartResponse.builder()
                .products(List.of(ProductResponse.valueOf(product)))
                .build();
    }

    private Cart buildNewCart() {
        return Cart.builder()
                .totalPrice(BigDecimal.ZERO)
                .build();
    }
}
