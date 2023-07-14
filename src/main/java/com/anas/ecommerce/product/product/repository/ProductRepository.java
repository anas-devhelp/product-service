package com.anas.ecommerce.product.product.repository;

import com.anas.ecommerce.product.category.entities.Category;
import com.anas.ecommerce.product.product.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
