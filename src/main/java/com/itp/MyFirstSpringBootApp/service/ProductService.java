package com.itp.MyFirstSpringBootApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.itp.MyFirstSpringBootApp.exception.ProductNotFoundException;
import com.itp.MyFirstSpringBootApp.model.Product;
import com.itp.MyFirstSpringBootApp.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    // Add static product
    public void addProduct(Product p) {
        productRepository.save(p);
    }

    // Add static product (method variant)
    public void addProduct1(Product p) {
        productRepository.save(p);
    }

    // Add product and return saved entity
    public Product addProduct2(Product p) {
        return productRepository.save(p);
    }

    // Get all products
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    // Get product by ID (with custom exception)
    public Optional<Product> getProductById(int pid) {
        Optional<Product> product = productRepository.findById(pid);

        if (product.isEmpty()) {
            throw new ProductNotFoundException("Product not found with ID: " + pid);
        }

        return product;
    }

    // Get products with price above 100
    public List<Product> getAllProductAbove100() {
        return productRepository.getAllProductAbove100();
    }

    // Get all products sorted by title
    public List<Product> getAllProductByTitle() {
        return productRepository.getAllProductByTitle();
    }

    // Get product by category
    public List<Product> getProductByCategory(String category) {
        return productRepository.findByProductCategory(category);
    }

    // Add multiple products
    public List<Product> addMultipleProductusingresponsebody(List<Product> products) {
        return productRepository.saveAll(products);
    }

    // Pagination
    public Page<Product> getProductByPagination(int pageNumber, int pageSize) {
        return productRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    // Sorting + Pagination
    public Page<Product> getProductBySortingAndPagination(String field, int pageNumber, int pageSize) {
        return productRepository.findAll(
                PageRequest.of(pageNumber, pageSize)
                        .withSort(Sort.by(Sort.Direction.ASC, field))
        );
    }

    // Delete product
    public void deleteProduct(int pid) {
        productRepository.deleteById(pid);
    }

    // Update product (no validation)
    public void updateProduct(int pid, Product newValues) {
        Optional<Product> optionalProduct = productRepository.findById(pid);

        if (optionalProduct.isPresent()) {
            Product productFromDB = optionalProduct.get();

            // Update fields with new values
            productFromDB.setProductTitle(newValues.getProductTitle());
            productFromDB.setProductDesc(newValues.getProductDesc());
            productFromDB.setProductCategory(newValues.getProductCategory());
            productFromDB.setPrice(newValues.getPrice());

            // Save updated product
            productRepository.save(productFromDB);
        } else {
            throw new RuntimeException("Product with ID " + pid + " not found.");
        }
    }
    //
    public List<Product> findByPriceGreaterThanEqual(double price) {
        return productRepository.findByPriceGreaterThanEqual(price);
    }

}
