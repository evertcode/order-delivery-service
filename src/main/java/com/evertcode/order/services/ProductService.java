package com.evertcode.order.services;

import com.evertcode.order.dtos.ProductDTO;
import com.evertcode.order.entities.Product;
import com.evertcode.order.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<ProductDTO> findAllByOrderByName() {
		List<Product> productList = productRepository.findAllByOrderByNameAsc();
		return productList.stream().map(ProductDTO::new).collect(Collectors.toList());
	}
}
