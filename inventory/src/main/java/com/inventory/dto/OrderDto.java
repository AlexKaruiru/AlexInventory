/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.dto;


import com.inventory.validations.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDto {

    @NotNull(message = "{client.code-field-required}")
    private Long client;
    @NotNull(message = "{total.order-field-required}")
    private BigDecimal total;
    @NotEmptyList(message = "{order.items-field-required}")
    private List<ItemOrderDto> itemOrders;
}