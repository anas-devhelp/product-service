package com.anas.ecommerce.product.category.controllers;

import com.anas.ecommerce.product.category.dto.CategoryDto;
import com.anas.ecommerce.product.category.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/category")
/**
 * This controller will provide data related to product categories.
 * CRUD operations are allowed as well as category listing along with product details
 * @author  Mohammad Anas
 * @version 1.0
 * @since   2023-07-15
 */
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryDto> getAll(){
        return  categoryService.readAll();
    }


    @GetMapping("/products")
    public List<CategoryDto> getProducts(){
        return categoryService.readAll();
    }

    @GetMapping("/{id}")
    public CategoryDto get(@PathVariable Long id){
        return categoryService.read(id);
    }

    @PostMapping
    public CategoryDto post(@RequestBody CategoryDto dto){
        return categoryService.create(dto);
    }

    @DeleteMapping("/{id}")
    public void post(@PathVariable Long id){
        categoryService.delete(id);
    }

    @PatchMapping("/{id}")
    public CategoryDto patch(@PathVariable Long id, @RequestBody CategoryDto dto) {
        return categoryService.update(id, dto);
    }

    @PutMapping("/{id}")
    public CategoryDto put(@PathVariable Long id, @RequestBody CategoryDto dto){
        return categoryService.update(id, dto);
    }
}
