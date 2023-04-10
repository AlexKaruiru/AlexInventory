/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.services;


import com.inventory.entities.Client;
import com.inventory.repositories.ClientRepository;
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
class ClientService {

    ClientRepository clientRepository;

    public Client getClientById(Long id){
        return clientRepository
                .findById(id)
                .orElseThrow(() -> {
                    throw new ResponseStatusException(NOT_FOUND,
                            "Client not found");
                });
    }

    public Client save(Client client){
        
        return clientRepository.save(client);
    }

    public void delete(Long id){
        clientRepository.findById(id)
                .map(client -> {
                    clientRepository.delete(client);
                    return client;
                })
                .orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Client not found"));
    }

    public void update(Client client,Long id){
        clientRepository
                .findById(id)
                .map(existingClient -> {
                    client.setId(existingClient.getId());
                    clientRepository.save(client);
                    return existingClient;
                }).orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Client not found"));
    }

    public List<Client> filterClients (Client clientFilter){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(clientFilter, matcher);
        return clientRepository.findAll(example);
    }
}