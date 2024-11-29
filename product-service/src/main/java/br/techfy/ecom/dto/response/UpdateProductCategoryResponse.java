package br.techfy.ecom.dto.response;

import br.techfy.ecom.model.Category;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record UpdateProductCategoryResponse(
        UUID id,
        String name
) {
    public static UpdateProductCategoryResponse valueOf(Category category) {
        return UpdateProductCategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
