/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.services;



import com.inventory.entities.Supplier;
import com.inventory.repositories.SupplierRepository;
import java.util.Collection;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;
   
    @Transactional
    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public Collection<Supplier> list() {
           return supplierRepository.findAll();
        
           }

    public Optional<Supplier> findById(Long id) {
        return supplierRepository.findById(id);
    }
    
   
    public Boolean delete(Long id) {
      supplierRepository.deleteById(id);          
          return true;
    }
}

