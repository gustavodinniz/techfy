package br.techfy.ecom.service;

import br.techfy.ecom.dto.request.CreateCategoryRequest;
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
}
