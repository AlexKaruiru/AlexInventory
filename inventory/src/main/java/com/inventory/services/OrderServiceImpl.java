/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.services;



import com.inventory.dto.ItemOrderDto;
import com.inventory.dto.OrderDto;
import com.inventory.entities.Client;
import com.inventory.entities.Item;
import com.inventory.entities.ItemOrder;
import com.inventory.entities.Order;
import com.inventory.enums.OrderStatus;
import com.inventory.repositories.ClientRepository;
import com.inventory.repositories.ItemOrderRepository;
import com.inventory.repositories.ItemRepository;
import com.inventory.repositories.OrderRepository;
import com.inventory.exceptions.Exception;
import com.inventory.exceptions.OrderNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service

public class OrderServiceImpl implements OrderService{

    final OrderRepository orderRepository;
    final ClientRepository clientRepository;
    final ItemRepository itemRepository;
    final ItemOrderRepository itemOrderRepository;

    @Override
    @Transactional
    public Order save(OrderDto orderDto)  {
       Long clientId = orderDto.getClient();
        Client client = clientRepository
                .findById(clientId)
                .orElseThrow(() -> new Exception("Invalid client code"));

        Order order = new Order();
        order.setTotal(orderDto.getTotal());
        order.setDateOrder(LocalDate.now());
        order.setClient(client);
        order.setOrderStatus(OrderStatus.DONE);

        var orderList = convertItems(order, orderDto.getItemOrders());
        orderRepository.save(order);
        itemOrderRepository.saveAll(orderList);
        order.setItemOrders(orderList);
        return order;
    }

    public Optional<Order> getCompletePurchase(Long id) {
        return orderRepository.findByIdFetchIAndItemOrders(id);
    }

    @Override
    @Transactional
    public void updateStatus(Long id, OrderStatus orderStatus) {
        orderRepository
                .findById(id)
                .map(p -> {
                    p.setOrderStatus(orderStatus);
                    return orderRepository.save(p);
                }).orElseThrow(() -> new OrderNotFoundException());
    }

    private List<ItemOrder> convertItems(Order order, List<ItemOrderDto> itemOrderDtos){
        if(itemOrderDtos.isEmpty()){
            try {
                throw new Exception("Unable to place orders without items");
            } catch (Exception ex) {
                Logger.getLogger(OrderServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return itemOrderDtos
                .stream()
                .map(dto -> {
                    Long itemId = dto.getItem();
                    Item item = itemRepository
                            .findById(itemId)
                            .orElseThrow(() -> new Exception("Invalid item code " + itemId));

                    ItemOrder itemOrder = new ItemOrder();
                    itemOrder.setQuantity(dto.getQuantity());
                    itemOrder.setOrder(order);
                    itemOrder.setItem(item);
                    return itemOrder;

                }).collect(Collectors.toList());
    }

    @Override
    public Optional<Order> getCompleteOrder(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
 }
