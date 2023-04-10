/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.dto;


import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderInformationDTO {

    private Long code;
    private String clientName;
    private BigDecimal total;
    private String purchaseDate;
    private String purchaseStatus;
    private List<ItemOrderInformationDTO> items;
}