package com.example.SpringRedisPet_20december.Controller.REST;

import com.example.SpringRedisPet_20december.Model.Product;
import com.example.SpringRedisPet_20december.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product/api")
public class ProductRESTController {

    @Autowired
    private ProductService operations;

    @GetMapping("/all")
    public List<Product> getAllProducts(){
        operations.getAllProducts().stream().forEach(product -> System.out.println(product.getName()));
        return operations.getAllProducts();
    }
    @GetMapping("/concrete/{id}")
    public Product getConcreteProduct(@PathVariable("id")int id){
        return operations.getConcreteProduct(id);
    }
    @PostMapping("/save")
    public ResponseEntity<HttpStatus> saveProduct(@RequestBody Product product){
        operations.saveProduct(product);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id")int id){
        operations.deleteProduct(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    //Кастомные эндпоинты

    @GetMapping("/all_by_name")
    public List<Product> allByName(@RequestBody String name){
        return operations.getAllByName(name);
    }
    @GetMapping("/all_by_category")
    public List<Product> allByCategory(@RequestBody String category){
        return operations.getAllByCategory(category);
    }
    @GetMapping("/all_by_price_less")
    public List<Product> allByPriceLess(@RequestBody int price){
        return operations.getAllByPriceLessThan(price);
    }
    @GetMapping("/all_by_price_between/{min}/{max}")
    public List<Product> allBetween(@PathVariable("min")int min,@PathVariable("max")int max){
        return operations.getAllByPriceBetween(min,max);
    }
    @GetMapping("/all_by_quantity")
    public List<Product> allByQuantityGreater(@RequestBody int quantity){
        return operations.getByQuantityGreater(quantity);
    }
}
