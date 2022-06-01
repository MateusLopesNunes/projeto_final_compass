package com.br.compass.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.br.compass.dto.ProductDto;
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
	public void testeDoTeste() {
        List<Product> productList = this.mockListProduct();
        
        productList.forEach(x -> System.out.println(x.toString()));
	}

	private List<Product> mockListProduct() {
		List<Product> products = new ArrayList();
		
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
