package com.itp.MyFirstSpringBootApp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itp.MyFirstSpringBootApp.model.Product;
import com.itp.MyFirstSpringBootApp.service.ProductService;

import jakarta.validation.Valid;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    // Add static product
    @PostMapping("/addProduct")
    public String addProduct() {
        Product p = Product.builder()
                .productTitle("Mobile")
                .productDesc("16 GB Ram 1TB SSD One Plus")
                .productCategory("Electronics")
                .price(100.0)
                .build();

        productService.addProduct(p);
        return "Product added Successfully";
    }

    // Add static product using another method
    @PostMapping("/addProduct1")
    public String addProduct1() {
        Product p = Product.builder()
                .productTitle("Boat")
                .productDesc("Wireless Headphones")
                .productCategory("Electronics")
                .price(100.0)
                .build();

        productService.addProduct1(p);
        return "Product added Successfully";
    }

    // Add static product and return saved entity
    @PostMapping("/addProduct2")
    public Product addProduct2() {
        Product p = Product.builder()
                .productTitle("Laptop")
                .productDesc("16 GB Ram 1TB SSD One Plus")
                .productCategory("Electronics")
                .price(100.0)
                .build();

        Product p1 = productService.addProduct2(p);
        return p1;
    }

    // Using RequestParam
    @PostMapping("/addProductusingrequestparam")
    public Product addProductusingrequestparam(
            @RequestParam("pTitle") String productTitle,
            @RequestParam("pDesc") String productDesc,
            @RequestParam("pCategory") String productCategory,
            @RequestParam("pPrice") double price) {

        Product p = Product.builder()
                .productTitle(productTitle)
                .productDesc(productDesc)
                .productCategory(productCategory)
                .price(price)
                .build();

        return productService.addProduct2(p);
    }

    // Using PathVariable
    @PostMapping("/addProductusingpathvariable/{a}/{b}/{c}/{d}")
    public Product addProductusingpathvariable(
            @PathVariable("a") String productTitle,
            @PathVariable("b") String productDesc,
            @PathVariable("c") String productCategory,
            @PathVariable("d") double price) {

        Product p = Product.builder()
                .productTitle(productTitle)
                .productDesc(productDesc)
                .productCategory(productCategory)
                .price(price)
                .build();

        return productService.addProduct2(p);
    }

    // Using RequestBody with validation
    @PostMapping("/addProductusingrequestbody")
    public Product addProductusingrequestbody(@Valid @RequestBody Product p) {
        return productService.addProduct2(p);
    }

    // Get all products
    @GetMapping("/getAllProduct")
    public List<Product> getAllProduct() {
        return productService.getAllProduct();
    }

    // Get product by ID
    @GetMapping("/getProductById/{pid}")
    public Optional<Product> getProductById(@PathVariable int pid) {
        return productService.getProductById(pid);
    }

    // Get all products above 100 price
    @GetMapping("/getAllProductAbove100")
    public List<Product> getAllProductAbove100() {
        return productService.getAllProductAbove100();
    }
    
    //
    @GetMapping("/findByPriceGreaterThanEqual/{price}")
    public List<Product> findByPriceGreaterThanEqual(@PathVariable double price) {
        return productService.findByPriceGreaterThanEqual(price);
    }


    // Get all products sorted by title
    @GetMapping("/getAllProductByTitle")
    public List<Product> getAllProductByTitle() {
        return productService.getAllProductByTitle();
    }

    // Get products by category
    @GetMapping("/getProductByCategory/{category}")
    public List<Product> getProductByCategory(@PathVariable("category") String category) {
        return productService.getProductByCategory(category);
    }

    // Add multiple products
    @PostMapping("/addMultipleProductusingresponsebody")
    public List<Product> addMultipleProductusingresponsebody(@RequestBody List<Product> products) {
        return productService.addMultipleProductusingresponsebody(products);
    }

    // Pagination
    @GetMapping("/getProductByPagination/{pageNumber}/{pageSize}")
    public Page<Product> getProductByPagination(@PathVariable int pageNumber, @PathVariable int pageSize) {
        return productService.getProductByPagination(pageNumber, pageSize);
    }

    // Sorting + Pagination
    @GetMapping("/getProductBySortingAndPagination/{field}/{pageNumber}/{pageSize}")
    public Page<Product> getProductBySortingAndPagination(
            @PathVariable String field,
            @PathVariable int pageNumber,
            @PathVariable int pageSize) {
        return productService.getProductBySortingAndPagination(field, pageNumber, pageSize);
    }

    // Delete product
    @DeleteMapping("/deleteProduct/{pid}")
    public String deleteProduct(@PathVariable int pid) {
        productService.deleteProduct(pid);
        return "Product Deleted";
    }

    // Update product (no validation here)
    @PutMapping("/updateProduct/{pid}")
    public String updateProduct(@RequestBody Product newValues, @PathVariable int pid) {
        productService.updateProduct(pid, newValues);
        return "Product Updated";
    }
}
