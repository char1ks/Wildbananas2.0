package com.example.SpringRedisPet_20december.Controller.REST;

import com.example.SpringRedisPet_20december.Model.Delivery;
import com.example.SpringRedisPet_20december.Model.Product;
import com.example.SpringRedisPet_20december.Security.VisitorDetails;
import com.example.SpringRedisPet_20december.Service.DeliveryService;
import com.example.SpringRedisPet_20december.Service.ProductService;
import com.example.SpringRedisPet_20december.Service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery/api")
public class DeliveryRESTController {

    @Autowired
    private DeliveryService operations;

    @Autowired
    private ProductService operationsProduct;

    @Autowired
    private VisitorService operationsVisitor;

    @PostMapping("/save/{product_id}")
    public ResponseEntity<HttpStatus> saveDelivery(@PathVariable("product_id")int product_id, @RequestBody Delivery delivery){
        Product product=operationsProduct.getConcreteProduct(product_id);
        product.setQuantity(product.getQuantity()-1);
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        VisitorDetails details= (VisitorDetails) authentication.getPrincipal();

        if(details.getVisitor().getMoney()>=product.getPrice()) {
            details.getVisitor().setMoney((int) (details.getVisitor().getMoney()-product.getPrice()));
            delivery.setVisitor(details.getVisitor());
            delivery.setProduct(product);

            operations.saveDelivery(delivery);
            operationsProduct.saveProduct(product);
            operationsVisitor.saveVisitor(details.getVisitor());
            return ResponseEntity.ok(HttpStatus.OK);
        }
        else
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
    }
}
