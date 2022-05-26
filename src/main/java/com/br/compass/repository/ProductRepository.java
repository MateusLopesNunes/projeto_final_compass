package com.br.compass.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.compass.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Optional<Product> findByName(String name);
	
}
