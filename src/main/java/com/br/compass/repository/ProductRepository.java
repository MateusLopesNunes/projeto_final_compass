package com.br.compass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.compass.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
}
