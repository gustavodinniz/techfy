package br.techfy.ecom.service;

import br.techfy.ecom.dto.request.CreateCategoryRequest;
import br.techfy.ecom.dto.request.UpdateCategoryRequest;
import br.techfy.ecom.dto.response.GetCategoriesResponse;
import br.techfy.ecom.dto.response.GetCategoryByIdResponse;
import br.techfy.ecom.exception.CategoryNotFoundException;
import br.techfy.ecom.model.Category;
import br.techfy.ecom.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public String createCategory(CreateCategoryRequest createCategoryRequest) {
        log.info("Starting operation to create category: {}", createCategoryRequest.name());
        var category = createCategoryRequest.toCategory();
        category = categoryRepository.save(category);
        log.info("Category created with ID: {}", category.getId());
        return category.getId().toString();
    }

    public GetCategoryByIdResponse getCategoryById(UUID id) {
        log.info("Starting operation to get category by ID: {}", id);
        var category = findCategoryById(id);
        log.info("Category found for ID: {}", id);
        return GetCategoryByIdResponse.valueOf(category);
    }

    public void deleteCategory(UUID id) {
        log.info("Starting operation to delete category by ID: {}", id);
        categoryRepository.deleteById(id);
        log.info("Category deleted for ID: {}", id);
    }

    public void updateCategory(UUID id, UpdateCategoryRequest updateCategoryRequest) {
        log.info("Starting operation to update category by ID: {}", id);
        var category = findCategoryById(id);
        category.setName(Optional.ofNullable(updateCategoryRequest.name()).orElse(category.getName()));
        category.setDescription(Optional.ofNullable(updateCategoryRequest.description()).orElse(category.getDescription()));
        categoryRepository.save(category);
        log.info("Category updated for ID: {}", id);
    }

    public Category findCategoryById(UUID id) {
        log.info("Find category by ID: {}", id);
        return categoryRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("No category found for ID: {}", id);
                    return new CategoryNotFoundException("404.001");
                });
    }

    public List<GetCategoriesResponse> getCategories(String name) {
        log.info("Starting operation to get categories");
        var category = new Category();
        category.setName(name);

        var matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withIgnoreNullValues()
                .withIgnorePaths("id", "description", "createdAt", "updatedAt")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var categoryExample = Example.of(category, matcher);
        var categories = categoryRepository.findAll(categoryExample)
                .stream()
                .map(GetCategoriesResponse::valueOf)
                .toList();

        log.info("Categories found. Size: {}", categories.size());
        return categories;
    }
}
