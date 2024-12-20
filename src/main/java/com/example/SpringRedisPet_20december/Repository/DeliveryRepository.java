package com.example.SpringRedisPet_20december.Repository;

import com.example.SpringRedisPet_20december.Model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery,Integer> {
}
