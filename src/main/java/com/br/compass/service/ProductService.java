package com.br.compass.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.compass.dto.ProductDto;
import com.br.compass.dto.ProductForm;
import com.br.compass.model.Product;
import com.br.compass.repository.ProductRepository;

@Service
public class ProductService {
	
	private ProductRepository productRepository;

	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<ProductDto> findAll() {
		List<Product> products = productRepository.findAll();
		return ProductDto.modelToDtoList(products);
 	}
	
	public ResponseEntity<ProductDto> saveProduct(ProductForm productForm, UriComponentsBuilder builder) {
		Product product = productRepository.save(productForm.dtoToProduct());
		URI uri = builder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProductDto(product));
	}
	
	public ResponseEntity<ProductDto> findById(Long id) {
		Optional<Product> product = productRepository.findById(id);
		
		if (product.isPresent()) {
			return ResponseEntity.ok(new ProductDto(product.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<?> deleteById(Long id) {
		Optional<Product> product = productRepository.findById(id);
		
		if (product.isPresent()) {
			productRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<ProductDto> updateProduct(ProductForm productForm, Long id) {
		Optional<Product> product = productRepository.findById(id);
		
		if (product.isPresent()) {
			Product update = productForm.update(id, productRepository);
			return ResponseEntity.ok(new ProductDto(update));
		}
		return ResponseEntity.notFound().build();
	}
}
