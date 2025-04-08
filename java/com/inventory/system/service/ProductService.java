package com.inventory.system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inventory.system.model.Product;
import com.inventory.system.repository.ProductRepository;

@Service
public class ProductService {
	private final ProductRepository productRepository;

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Optional<Product> getProductById(Long id) {
		return productRepository.findById(id);
	}

	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

	public List<Product> getLowStockProducts() {
		// Assuming reorder threshold is stored with each product
		return productRepository.findAll().stream().filter(p -> p.getQuantity() < p.getReorderThreshold()).toList();
	}
}