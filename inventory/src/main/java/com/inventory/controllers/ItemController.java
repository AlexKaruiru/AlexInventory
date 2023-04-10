/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.controllers;


import com.inventory.entities.Item;
import com.inventory.services.ItemService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

@AllArgsConstructor

@RestController
@RequestMapping("api/items")
@Api("Api Products")

public class ItemController {

    private ItemService itemService;

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation("Register an item")
    @ApiResponses({
            @ApiResponse(code= 201, message ="Product registered succcessfully"),
            @ApiResponse(code=400, message = "Validation error")
    })
    public Item save (@RequestBody @Valid Item item){//
        return itemService.save(item);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Change an item")
    @ApiResponses({
            @ApiResponse(code= 204, message = "Item changed successfully"),
            @ApiResponse(code=404, message = "Item not found for the given id")
    })
    public void update (@RequestBody @Valid Item item, @PathVariable @ApiParam("Item id") Long id){//
        itemService.update(item, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Delete an Item")
    @ApiResponses({
            @ApiResponse(code= 204, message = "Itemt deleted successfully"),
            @ApiResponse(code=404, message = "Item not found for the given id")
    })
    public void delete (@PathVariable @ApiParam("Item id") Long id){
        itemService.delete((id));
    }

    @GetMapping("/{id}")
    @ApiOperation("Find a product")
    @ApiResponses({
            @ApiResponse(code= 200, message = "Product Found"),
            @ApiResponse(code=404, message = "Product not found for the given id")
    })
    public Item getById(@PathVariable Long id){
        return itemService.getById(id);
    }

    @GetMapping
    @ApiOperation("List all items")
    @ApiResponses({
            @ApiResponse(code= 200, message = "Items listed"),
    })
    public List<Item> findAll(Item itemFilter){
        
        return itemService.findAll(itemFilter);
    }
}