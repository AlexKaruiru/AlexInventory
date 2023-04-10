/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.services;


import com.inventory.entities.Item;
import com.inventory.repositories.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


import static org.springframework.http.HttpStatus.*;

@AllArgsConstructor

@Service
public
class ItemService {

    ItemRepository itemRepository;

    public Item save(Item item){
        return itemRepository.save(item);
    }

    public void update(Item item, Long id){
        itemRepository
                .findById(id)
                .map( p -> {
                    item.setId(p.getId());
                    itemRepository.save(item);
                    return item;
                }).orElseThrow( () ->
                        new ResponseStatusException(NOT_FOUND, "Item not found"));
    }

    public void delete(Long id){
        itemRepository
                .findById(id)
                .map( p -> {
                    itemRepository.delete(p);
                    return Void.TYPE;
                }).orElseThrow( () ->
                        new ResponseStatusException(NOT_FOUND, "Item not found"));
    }

    public Item getById(Long id){
        return itemRepository
                .findById(id)
                .orElseThrow( () ->
                        new ResponseStatusException(NOT_FOUND, "Item not found"));
    }

    public List<Item> findAll(Item itemFilter){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(itemFilter, matcher);
        return itemRepository.findAll(example);
    }
}
