/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Table(name = "tb_suppliers")
public class Supplier {
    @Id
    @Column(name = "supplier_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "supplier_name")
    private String name;
    @Column(name = "supplier_contact")
    private String phoneNumber;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "description")
    private String description;
    
    
    }