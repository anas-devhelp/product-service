package com.anas.ecommerce.product.category.dto;
public class CategoryDto {

    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private String slug;

    public CategoryDto(){

    }

    public CategoryDto(String title, String description, String imageUrl, String slug) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.slug = slug;
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
}
