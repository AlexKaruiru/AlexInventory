/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.controllers;

import com.inventory.dto.ItemOrderInformationDTO;
import com.inventory.dto.OrderDto;
import com.inventory.dto.OrderInformationDTO;
import com.inventory.dto.UpdateOrderStatusDTO;
import com.inventory.enums.OrderStatus;
import com.inventory.entities.ItemOrder;
import com.inventory.entities.Order;
import com.inventory.services.OrderServiceImpl;

import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@AllArgsConstructor

@RestController
@RequestMapping("api/orders")
@Api("Api Requests")
public class OrderController {

    OrderServiceImpl orderService;

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation("Register an order")
    @ApiResponses({
            @ApiResponse(code= 201, message ="Request successfully registered"),
            @ApiResponse(code=400, message = "Validation error")
    })
    public Long save (@RequestBody @Valid OrderDto orderDto){
        Order purchase = orderService.save(orderDto);
        return purchase.getId();
    }

    @GetMapping("/{id}")
    @ApiOperation("Find an order")
    @ApiResponses({
            @ApiResponse(code= 200, message = "Order found"),
            @ApiResponse(code=404, message = "Order not found for the given id")
    })
    public OrderInformationDTO getById(@PathVariable @ApiParam("Order id") Long id){
        return orderService
                .getCompletePurchase(id)
                .map( p -> convert(p))
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Order not found"));
    }

    private OrderInformationDTO convert (Order order){
        return OrderInformationDTO
                .builder()
                .code(order.getId())
                .purchaseDate(order.getDateOrder().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .clientName(order.getClient().getName())
                .total(order.getTotal())
                .purchaseStatus(order.getOrderStatus().name())
                .items(convertInformation(order.getItemOrders()))
                .build();
    }

    @PatchMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Change an order")
    @ApiResponses({
            @ApiResponse(code= 204, message = "Order changed successfully"),
            @ApiResponse(code=404, message = "Order not found for the given id")
    })
    public void updateOrder(@RequestBody UpdateOrderStatusDTO updateOrderStatusDTO, @PathVariable @ApiParam("Order id")Long id){
        String newStatus = updateOrderStatusDTO.getNewStatus();
        orderService.updateStatus(id, OrderStatus.valueOf(newStatus));
    }

    private List<ItemOrderInformationDTO> convertInformation (List<ItemOrder> itemOrders){
        if(CollectionUtils.isEmpty(itemOrders)){
            return Collections.emptyList();
        }

        return itemOrders
                .stream()
                .map(item -> ItemOrderInformationDTO
                        .builder()
                        .itemDescription(item.getItem().getDescription())
                        .unitPrice(item.getItem().getPrice())
                        .quantity(item.getQuantity())
                        .build())
                .collect(Collectors.toList());
    }
}

