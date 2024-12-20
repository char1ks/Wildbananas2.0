package com.example.SpringRedisPet_20december.Repository;

import com.example.SpringRedisPet_20december.Model.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor,Integer> {
    Visitor findByUsername(String username);
}
