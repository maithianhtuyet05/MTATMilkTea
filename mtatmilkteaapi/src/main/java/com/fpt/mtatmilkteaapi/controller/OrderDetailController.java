package com.fpt.mtatmilkteaapi.controller;

import com.fpt.mtatmilkteaapi.repository.IOrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/orderdetail")
public class OrderDetailController {

    @Autowired
    private IOrderDetailRepository orderDetailRepository;

    @GetMapping("/list")
    public ResponseEntity<?> getOrderDetails() {
        return ResponseEntity.ok(orderDetailRepository.findAll());
    }
}
