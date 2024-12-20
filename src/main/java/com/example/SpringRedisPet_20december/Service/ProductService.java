package com.example.SpringRedisPet_20december.Service;

import com.example.SpringRedisPet_20december.Model.Product;
import com.example.SpringRedisPet_20december.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository operations;

    public List<Product> getAllProducts(){
        return operations.findAll();
    }
    @Cacheable(value = "product", key = "#id")
    public Product getConcreteProduct(int id){
        return operations.findById(id).orElse(null);
    }
    @CachePut(value = "product", key = "#result.id")
    public Product saveProduct(Product product){
        return operations.save(product);
    }
    @CacheEvict(value = "product", key = "#id")
    public void deleteProduct(int id){
        operations.deleteById(id);
    }

    //Кастомные методы

    public List<Product> getAllByName(String name){
        return operations.findByName(name);
    }
    public List<Product> getAllByCategory(String category){
        return operations.findByCategory(category);
    }
    public List<Product> getAllByPriceLessThan(double price){
        return operations.findByPriceLessThan(price);
    }
    public List<Product> getAllByPriceBetween(double min,double max){
        return operations.findByPriceBetween(min,max);
    }
    public List<Product> getByQuantityGreater(int quantity){
        return operations.findByQuantityGreaterThan(quantity);
    }
}
