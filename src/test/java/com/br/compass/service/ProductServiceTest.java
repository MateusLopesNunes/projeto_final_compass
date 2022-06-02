package com.br.compass.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.br.compass.model.Product;
import com.br.compass.repository.ProductRepository;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("ProductServiceTest")
public class ProductServiceTest {
	
	@MockBean
	private ProductRepository productRepository;
	
	@Autowired
	private ProductService productService;
	
	@Test
	public void mustFindAll() {
		List<Product> productsMock = mockListProduct();
		
		Mockito.when(productRepository.findAll()).thenReturn(productsMock);
		assertEquals(productsMock, productRepository.findAll());
	}
	
	@Test
	public void mustFindById() {
		Product productMock = mockProduct(1L);
		
		Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(productMock));
		assertEquals(productRepository.findById(1L).get(), productMock);
	}
	
	@Test
	public void mustSave() {
		Product productMock = mockProduct(1L);
		
		productRepository.save(productMock);
		Mockito.verify(productRepository).save(productMock);
	}
	
	@Test
	public void mustUpdate() {
		Product productMock = mockProduct(1L);
		Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(productMock));
		
		Product product = productRepository.findById(1L).get();
		product.setId(2L);
		product.setName("Produto2"); 
		product.setDescription("descrição2");
		product.setPrice(3545.0);
		Product save = productRepository.save(product);
		System.out.println(save);
		
		assertEquals(save, product);
		
		/*Product productMock = mockProduct(1L);
		
		productRepository.save(productMock);
		Mockito.verify(productRepository).save(productMock);*/
	}
	
	@Test
	public void mustDeleteProductById() {
		productService.deleteById(1L);
		Mockito.verify(productRepository).deleteById(1L);;
	}

	private List<Product> mockListProduct() {
		List<Product> products = new ArrayList<>();
		
		for (Long i = 0L; i < 5; i++) {
			products.add(mockProduct(i + 1));
		}
		return products;
	}

	private Product mockProduct(Long id) {
		// TODO Auto-generated method stub
		Product product = new Product("Product", "Description", 3000.0);
		product.setId(id);
		return product;
	}
}
