package com.br.compass.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.compass.dto.ProductDto;
import com.br.compass.dto.ProductForm;

public interface ProductService {

	public Page<ProductDto> findAll(Pageable page);

	public ResponseEntity<ProductDto> saveProduct(ProductForm productForm, UriComponentsBuilder builder);

	public ResponseEntity<ProductDto> findById(Long id);

	public ResponseEntity<?> deleteById(Long id);

	public ResponseEntity<ProductDto> updateProduct(ProductForm productForm, Long id);

	public Page<ProductDto> search(@RequestParam(required = false) Double maxPrice,
			@RequestParam(required = false) Double minPrice, @RequestParam(required = false) String q, Pageable page);
}
