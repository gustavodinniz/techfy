package br.techfy.ecom.service;

import br.techfy.ecom.dto.request.CreateCategoryRequest;
import br.techfy.ecom.model.Category;
import br.techfy.ecom.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    private static CreateCategoryRequest createCategoryRequest;

    @BeforeAll
    public static void setUp() {
        createCategoryRequest = new CreateCategoryRequest("Category 1", "Category 1 description");
    }

    @Test
    void shouldCreateCategoryWithSuccess() {
        givenCategoryRepositorySaveReturn();
        whenCreateCategory();
        thenExpectCategoryRepositorySaveCalledOnce();
        thenExpectCreateCategoryReturnId();
    }

    // GIVEN

    private void givenCategoryRepositorySaveReturn() {
        doReturn(buildCategory(createCategoryRequest.name(), createCategoryRequest.description()))
                .when(categoryRepository).save(any(Category.class));
    }


    // WHEN

    private void whenCreateCategory() {
        categoryService.createCategory(createCategoryRequest);
    }

    // THEN

    private void thenExpectCreateCategoryReturnId() {
        assertNotNull(categoryService.createCategory(createCategoryRequest));
    }

    private void thenExpectCategoryRepositorySaveCalledOnce() {
        verify(categoryRepository).save(any(Category.class));
    }

    // BUILD

    private Category buildCategory(String name, String description) {
        return Category.builder()
                .id(UUID.randomUUID())
                .name(name)
                .description(description)
                .build();
    }
}