package com.anas.ecommerce.product.category.service;

import com.anas.ecommerce.product.category.dto.CategoryDto;
import com.anas.ecommerce.product.category.entities.Category;
import com.anas.ecommerce.product.category.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public CategoryDto create(CategoryDto dto){
        Category category = getAsCategory(dto);
        Category saved = categoryRepository.save(category);
        dto.setId(saved.getId());
        return dto;
    }

    public CategoryDto read(Long id){
        Category c = categoryRepository.findById(id).get();
        return getAsCategoryDto(c);
    }

    public List<CategoryDto> readAll(){
        return getAsCategoryDtos(categoryRepository.findAll());
    }

    public CategoryDto update(Long id, CategoryDto dto){
        Category category = categoryRepository.findById(id).get();
        setAsCategory(category, dto);
        Category saved = categoryRepository.save(category);
        dto.setId(saved.getId());
        return dto;
    }

    public void delete(Long id){
        categoryRepository.deleteById(id);
    }

    public CategoryDto getAsCategoryDto(Category e){
        CategoryDto dto = new CategoryDto();
        dto.setId(e.getId());
        dto.setTitle(e.getTitle());
        dto.setDescription(e.getDescription());
        dto.setImageUrl(e.getImageUrl());
        dto.setSlug(e.getSlug());
        return dto;
    }
    private Category getAsCategory(CategoryDto dto){
        Category category = new Category();
        setAsCategory(category, dto);
        return category;
    }

    private void setAsCategory(Category category, CategoryDto e){
        category.setTitle(e.getTitle());
        category.setDescription(e.getDescription());
        category.setImageUrl(e.getImageUrl());
        category.setSlug(e.getSlug());
    }

    private List<CategoryDto> getAsCategoryDtos(List<Category> list){
        List<CategoryDto> result = new ArrayList<>(list.size());
        list.forEach(c->{
            result.add(getAsCategoryDto(c));
        });
        return result;
    }

}
