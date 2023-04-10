/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.controllers;

import com.inventory.entities.Client;
import com.inventory.services.ClientService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

@AllArgsConstructor

@RestController
@RequestMapping("api/clients")
@Api("Api Clients")
public class ClientController {

    private ClientService clientService;

    @GetMapping("/{id}")
    @ApiOperation("Get details for a particular Client")
    @ApiResponses({
            @ApiResponse(code= 200, message ="Client found"),
            @ApiResponse(code=404, message = "Client not found for given id")
    })
    public Client getClientById (@PathVariable @ApiParam("Client id") Long id){
        return clientService.getClientById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation("Save a new client")
    @ApiResponses({
            @ApiResponse(code= 201, message = "Client saved successfully"),
            @ApiResponse(code=400, message = "Validation error")
    })
    public Client save (@RequestBody @Valid Client client){
        return clientService.save(client);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Delete a Client")
    @ApiResponses({
            @ApiResponse(code= 204, message = "Client deleted successfully"),
            @ApiResponse(code=404, message = "Client not found for given id")
    })
    public void delete(@PathVariable @ApiParam("Client Id") Long id){
        clientService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Change a Client")
    @ApiResponses({
            @ApiResponse(code= 204, message = "Client changed successfully"),
            @ApiResponse(code=404, message = "Client not found for given id")
    })
    public void update(@RequestBody @Valid Client client, @PathVariable @ApiParam("client id") Long id){
        clientService.update(client, id);
    }

    @GetMapping
    @ApiOperation("Filter / Get Clients")
    public List<Client> filterClients (Client clientFilter){
        return clientService.filterClients(clientFilter);
    }
    
    
}
