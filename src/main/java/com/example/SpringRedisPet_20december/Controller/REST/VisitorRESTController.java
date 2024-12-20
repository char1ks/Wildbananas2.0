package com.example.SpringRedisPet_20december.Controller.REST;

import com.example.SpringRedisPet_20december.Model.Visitor;
import com.example.SpringRedisPet_20december.Security.VisitorDetails;
import com.example.SpringRedisPet_20december.Service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/visitor/api")
public class VisitorRESTController {

    @Autowired
    private VisitorService operations;

    @GetMapping("/current")
    public Visitor currentVisitor(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        VisitorDetails details= (VisitorDetails) authentication.getPrincipal();
        System.out.println(details.getVisitor());
        return details.getVisitor();
    }
    @PostMapping("/save")
    public ResponseEntity<HttpStatus> saveVisitor(@RequestBody Visitor visitor){
        operations.saveVisitor(visitor);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
