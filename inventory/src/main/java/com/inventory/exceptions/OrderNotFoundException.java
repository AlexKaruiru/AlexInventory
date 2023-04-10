/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.exceptions;


public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException() {
        super("order not found");
    }
}
