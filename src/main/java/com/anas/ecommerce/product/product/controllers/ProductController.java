package com.anas.ecommerce.product.product.controllers;

import com.anas.ecommerce.product.category.dto.CategoryDto;
import com.anas.ecommerce.product.product.dto.ProductDto;
import com.anas.ecommerce.product.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> getAll(){
        return productService.readAll();
    }

    @GetMapping("/{id}")
    public ProductDto get(@PathVariable Long id){
        return productService.read(id);
    }

    @PostMapping
    public ProductDto post(@RequestBody ProductDto dto){
        return productService.create(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        productService.delete(id);
    }

    @PatchMapping("/{id}")
    public ProductDto patch(@PathVariable Long id, @RequestBody ProductDto dto){
        return productService.update(id, dto);
    }

    @PutMapping("/{id}")
    public ProductDto put(@PathVariable Long id, @RequestBody ProductDto dto){
        return productService.update(id, dto);
    }

}
