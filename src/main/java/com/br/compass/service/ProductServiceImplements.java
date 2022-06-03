package com.br.compass.service;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.compass.dto.ProductDto;
import com.br.compass.dto.ProductForm;
import com.br.compass.model.Product;
import com.br.compass.repository.ProductRepository;

@Service
public class ProductServiceImplements implements ProductService {

	private ProductRepository productRepository;

	@Autowired
	public ProductServiceImplements(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Page<ProductDto> findAll(Pageable page) {
		Page<Product> products = productRepository.findAll(page);
		return ProductDto.modelToDtoPage(products);
	}

	@Override
	public ResponseEntity<ProductDto> saveProduct(ProductForm productForm, UriComponentsBuilder builder) {
		Product product = productRepository.save(productForm.dtoToProduct());
		URI uri = builder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProductDto(product));
	}

	@Override
	public ResponseEntity<ProductDto> findById(Long id) {
		Optional<Product> product = productRepository.findById(id);
		return ResponseEntity.ok(new ProductDto(product.get()));
	}

	@Override
	public ResponseEntity<ProductDto> deleteById(Long id) {
		Optional<Product> product = productRepository.findById(id);
		productRepository.deleteById(id);
		return ResponseEntity.ok(new ProductDto(product.get()));
	}

	@Override
	public ResponseEntity<ProductDto> updateProduct(ProductForm productForm, Long id) {
		Optional<Product> product = productRepository.findById(id);
		Product update = productForm.update(id, productRepository);
		return ResponseEntity.ok(new ProductDto(update));
	}

	@Override
	public Page<ProductDto> search(Double maxPrice, Double minPrice, String q, Pageable page) {
		Page<Product> product = productRepository.findByName(maxPrice, minPrice, q, page);
		return ProductDto.modelToDtoPage(product);
	}
}
