package com.myapp.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.myapp.base.entity.Product;
@Component
public interface ProductRepository extends JpaRepository<Product,Long> {

}
