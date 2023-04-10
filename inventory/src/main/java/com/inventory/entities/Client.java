/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import lombok.*;

import javax.persistence.*;
import java.util.Set;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "client_id")
    private Long id;
    @Column(name = "name", nullable = false, length = 60)
    @NotEmpty(message = "{name.field-required}")
    private String name;
    @Column(name = "Email")
    private String email;
    @Column(name = "Contact")
    private String contact;
    @Column(name = "Department")
    private String department;
    
    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Order> orders;

            
    
   }
