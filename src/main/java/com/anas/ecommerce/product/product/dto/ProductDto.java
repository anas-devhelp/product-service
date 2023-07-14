package com.anas.ecommerce.product.product.dto;

import com.anas.ecommerce.product.category.dto.CategoryDto;

public class ProductDto {

    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private String slug;

    private CategoryDto category;

    public ProductDto(){

    }

    public ProductDto(String title, String description, String imageUrl, String slug, CategoryDto category) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.slug = slug;
        this.category = category;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }
}
