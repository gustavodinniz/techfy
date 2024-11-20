package br.techfy.ecom.dto.response;

import br.techfy.ecom.model.Category;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record GetCategoryByIdResponse(
        UUID id,
        String name,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
){
    public static GetCategoryByIdResponse valueOf(Category category) {
        return GetCategoryByIdResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }
}
