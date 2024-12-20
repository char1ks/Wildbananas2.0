package com.example.SpringRedisPet_20december.Repository;

import com.example.SpringRedisPet_20december.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    // Поиск продукта по имени
    List<Product> findByName(String name);

    // Поиск продуктов по категории
    List<Product> findByCategory(String category);

    // Поиск продуктов с ценой меньше указанной
    List<Product> findByPriceLessThan(double price);

    // Поиск продуктов с ценой в заданном диапазоне
    List<Product> findByPriceBetween(double minPrice, double maxPrice);

    // Поиск продуктов по количеству на складе
    List<Product> findByQuantityGreaterThan(int quantity);
}