package br.techfy.ecom.service;

import br.techfy.ecom.client.ProductClient;
import br.techfy.ecom.dto.GetProductByIdDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductClient productClient;

    public GetProductByIdDTO getProductById(UUID productId) {
        try {
            return productClient.getProductById(productId);
        } catch (Exception e) {
            log.error("Error getting product by id: {}", productId, e);
            throw e;
        }

    }
}
