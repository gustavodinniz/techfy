package br.techfy.ecom.client;

import br.techfy.ecom.dto.GetProductByIdDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "product-service", url = "http://localhost:8080/api/v1/products")
public interface ProductClient {

    @GetMapping("/{id}")
    GetProductByIdDTO getProductById(@PathVariable UUID id);
}
