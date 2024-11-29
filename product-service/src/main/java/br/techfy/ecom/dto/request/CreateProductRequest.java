package br.techfy.ecom.dto.request;

import br.techfy.ecom.model.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateProductRequest(
        @NotBlank String name,
        @NotBlank String description,
        @NotNull BigDecimal price,
        @NotNull Integer stockQuantity,
        @NotBlank String sku,
        @NotNull UUID categoryId
){

    public Product toProduct() {
        Product product = new Product();
        product.setName(this.name());
        product.setDescription(this.description());
        product.setPrice(this.price());
        product.setStockQuantity(this.stockQuantity());
        product.setSku(this.sku());
        return product;
    }

}
