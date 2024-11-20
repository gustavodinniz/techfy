package br.techfy.ecom.controller;

import br.techfy.ecom.dto.request.CreateCategoryRequest;
import br.techfy.ecom.dto.request.UpdateCategoryRequest;
import br.techfy.ecom.dto.response.GetCategoryByIdResponse;
import br.techfy.ecom.service.CategoryService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategory(@RequestBody @Valid CreateCategoryRequest createCategoryRequest, HttpServletResponse response) {
        var uuid = categoryService.createCategory(createCategoryRequest);
        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(uuid)
                .toUri();
        response.addHeader("location", location.toString());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetCategoryByIdResponse getCategoryById(@PathVariable UUID id) {
        return categoryService.getCategoryById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCategory(@PathVariable UUID id, @RequestBody UpdateCategoryRequest updateCategoryRequest) {
        categoryService.updateCategory(id, updateCategoryRequest);
    }
}
