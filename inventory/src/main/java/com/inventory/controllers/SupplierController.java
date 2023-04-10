/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.controllers;


import com.inventory.entities.Supplier;
import com.inventory.services.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

        @GetMapping
    public ResponseEntity<Collection<Supplier>> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.list());
    }

    @PostMapping
    public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier) {
        return ResponseEntity.ok(supplierService.save(supplier));
    }

    @GetMapping("/{id}")
    ResponseEntity<Supplier> findSupplier(@PathVariable Long id) {
        Optional<Supplier> byId = supplierService.findById(id);

        return byId.map(ResponseEntity::ok).orElse(null);
    }
    
    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> delete (@PathVariable Long id){
        return ResponseEntity.ok(supplierService.delete(id));
    }
}