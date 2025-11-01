package com.itp.MyFirstSpringBootApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.itp.MyFirstSpringBootApp.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    // ✅ Custom native query — fetch all products with price >= 100
    @Query(value = "SELECT * FROM product WHERE price >= 100", nativeQuery = true)
    List<Product> getAllProductAbove100();

    // ✅ Custom native query — fetch all products sorted by title (ascending)
    @Query(value = "SELECT * FROM product ORDER BY product_title ASC", nativeQuery = true)
    List<Product> getAllProductByTitle();

    // ✅ Derived query method — find products by category
    List<Product> findByProductCategory(String productCategory);

	List<Product> findByPriceGreaterThanEqual(double price);

}
