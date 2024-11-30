package br.techfy.ecom.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class GetProductByIdDTO {

    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
}
