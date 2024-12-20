package com.example.SpringRedisPet_20december.Service;

import com.example.SpringRedisPet_20december.Model.Delivery;
import com.example.SpringRedisPet_20december.Repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DeliveryService {

    @Autowired
    private DeliveryRepository operations;

    public List<Delivery>getAllDeliveries(){
        return operations.findAll();
    }
    public Delivery getConcreteDelivery(int id){
        return operations.findById(id).orElse(null);
    }
    public Delivery saveDelivery(Delivery delivery){
        return operations.save(delivery);
    }
    public void deleteDelivery(int id){
        operations.deleteById(id);
    }
}
