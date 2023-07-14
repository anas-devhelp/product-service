package com.anas.ecommerce.product.product.service;

import com.anas.ecommerce.product.category.dto.CategoryDto;
import com.anas.ecommerce.product.category.entities.Category;
import com.anas.ecommerce.product.category.repository.CategoryRepository;
import com.anas.ecommerce.product.category.service.CategoryService;
import com.anas.ecommerce.product.product.dto.ProductDto;
import com.anas.ecommerce.product.product.entities.Product;
import com.anas.ecommerce.product.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    private CategoryService categoryService;
    private CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryService categoryService, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    public ProductDto create(ProductDto dto){
        Product product = getAsProduct(dto);
        Product saved = productRepository.save(product);
        dto.setId(saved.getId());
        return dto;
    }

    public ProductDto read(Long id){
        return getAsProductDto(productRepository.findById(id).get());
    }

    public List<ProductDto> readAll(){
        return getAsProductDtos(productRepository.findAll());
    }

    public ProductDto update(Long id, ProductDto dto){
        Product product = productRepository.findById(id).get();
        setAsProduct(product, dto);
        Product saved = productRepository.save(product);
        dto.setId(saved.getId());
        return dto;
    }

    public void delete(Long id){
        productRepository.deleteById(id);
    }

    private ProductDto getAsProductDto(Product e){
        ProductDto dto = new ProductDto();
        dto.setId(e.getId());
        dto.setTitle(e.getTitle());
        dto.setDescription(e.getDescription());
        dto.setImageUrl(e.getImageUrl());
        dto.setSlug(e.getSlug());
        dto.setCategory(categoryService.getAsCategoryDto(e.getCategory()));
        return dto;
    }
    private Product getAsProduct(ProductDto dto){
        Product product = new Product();
        setAsProduct(product, dto);
        return product;
    }
    private void setProductCategory(Product product, Long categoryId){
        product.setCategory(categoryRepository.findById(categoryId).get());
    }
    private void setAsProduct(Product product, ProductDto productDto){
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        product.setSlug(productDto.getSlug());
        setProductCategory(product, productDto.getCategory().getId());
    }

    private List<ProductDto> getAsProductDtos(List<Product> list){
        List<ProductDto> result = new ArrayList<>(list.size());
        list.forEach(c->{
            result.add(getAsProductDto(c));
        });
        return result;
    }

}
