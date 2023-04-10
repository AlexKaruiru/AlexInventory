/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.services;


import com.inventory.dto.OrderDto;
import com.inventory.entities.Order;
import com.inventory.enums.OrderStatus;
import java.util.Optional;

interface OrderService {
    Order save(OrderDto orderDto);
    Optional<Order> getCompleteOrder(Long id);
    void updateStatus(Long id, OrderStatus orderStatus);
}