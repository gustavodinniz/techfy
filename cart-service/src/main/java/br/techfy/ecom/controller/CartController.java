package br.techfy.ecom.controller;

import br.techfy.ecom.dto.response.AddProductToCartResponse;
import br.techfy.ecom.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")
public class CartController {

    private final CartService cartService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/products/{productId}/quantity/{quantity}")
    public AddProductToCartResponse addProductToCart(@PathVariable UUID productId, @PathVariable Integer quantity) {
        return cartService.addProductToCart(productId, quantity);
    }
}
