package com.mimaraslan.service;

import com.mimaraslan.model.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientService {

     ResponseEntity<List<Client>> getAllClients(String firstName) ;

     ResponseEntity<Client> getClientById(Integer id) ;

     ResponseEntity<Client> createClient(Client client) ;

     ResponseEntity<Client> updateClient(Integer id, Client client);

     ResponseEntity<HttpStatus> deleteClient(Integer id) ;

     ResponseEntity<HttpStatus> deleteAllClients() ;

     ResponseEntity<List<Client>> findByConfirmed();

}
