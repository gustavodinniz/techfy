package br.techfy.ecom.dto.request;

import br.techfy.ecom.model.Category;
import jakarta.validation.constraints.NotBlank;

public record CreateCategoryRequest(
        @NotBlank String name,
        @NotBlank String description
) {

    public Category toCategory() {
        Category category = new Category();
        category.setName(this.name());
        category.setDescription(this.description());
        return category;
    }
}
