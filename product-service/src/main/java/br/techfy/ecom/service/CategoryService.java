package br.techfy.ecom.service;

import br.techfy.ecom.dto.request.CreateCategoryRequest;
import br.techfy.ecom.dto.response.GetCategoryByIdResponse;
import br.techfy.ecom.exception.CategoryNotFoundException;
import br.techfy.ecom.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public UUID createCategory(CreateCategoryRequest createCategoryRequest) {
        log.info("Starting operation to create category: {}", createCategoryRequest.name());
        var category = createCategoryRequest.toCategory();
        categoryRepository.save(category);
        log.info("Category created with ID: {}", category.getId());
        return category.getId();
    }

    public GetCategoryByIdResponse getCategoryById(UUID id) {
        log.info("Starting operation to get category by ID: {}", id);
        return categoryRepository.findById(id)
                .map(category -> {
                    log.info("Category found for ID: {}", id);
                    return GetCategoryByIdResponse.valueOf(category);
                })
                .orElseThrow(() -> {
                    log.warn("No category found for ID: {}", id);
                    return new CategoryNotFoundException("404.001");
                });
    }
}
