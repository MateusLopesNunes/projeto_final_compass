package com.br.compass.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.compass.dto.ProductDto;
import com.br.compass.dto.ProductForm;
import com.br.compass.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	private ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public Page<ProductDto> list(Pageable page) {
		return productService.findAll(page);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> findById(@PathVariable Long id) {
		return productService.findById(id);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ProductDto> create(@RequestBody @Valid ProductForm productForm, UriComponentsBuilder builder) {
		return productService.saveProduct(productForm, builder);
	}
	
	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return productService.deleteById(id);
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<ProductDto> update(@RequestBody @Valid ProductForm productForm, @PathVariable Long id) {
		return productService.updateProduct(productForm, id);
	}
	
	@GetMapping("/search")
	public Page<ProductDto> search(@RequestParam(required = false) Double maxPrice, @RequestParam(required = false) Double minPrice, @RequestParam(required = false) String q, Pageable page) {
		return productService.search(maxPrice, minPrice, q, page);
	}
}
