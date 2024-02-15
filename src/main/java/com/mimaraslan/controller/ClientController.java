package com.mimaraslan.controller;

import java.util.List;
import com.mimaraslan.model.Client;
import com.mimaraslan.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


//  http://localhost:8080/api

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ClientController {

	private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    //  http://localhost:8080/api/clients
	@GetMapping("/clients")
	public ResponseEntity<List<Client>> getAllClients(@RequestParam(required = false) String firstName) {
			return clientService.getAllClients(firstName);
	}

	//  http://localhost:8080/api/clients/1
	@GetMapping("/clients/{id}")
	public ResponseEntity<Client> getClientById(@PathVariable("id") Integer id) {
		return clientService.getClientById(id);
	}


	//  http://localhost:8080/api/clients
	@PostMapping("/clients")
	public ResponseEntity<Client> createClient(@RequestBody Client client) {
		return clientService.createClient(client);
	}

	//  http://localhost:8080/api/clients/1
	@PutMapping("/clients/{id}")
	public ResponseEntity<Client> updateClient(@PathVariable("id") Integer id, @RequestBody Client client) {
		return clientService.updateClient(id, client);
	}

	//  http://localhost:8080/api/clients/1
	@DeleteMapping("/clients/{id}")
	public ResponseEntity<HttpStatus> deleteClient(@PathVariable("id") Integer id) {
		return clientService.deleteClient(id);
	}

	//  http://localhost:8080/api/clients
	@DeleteMapping("/clients")
	public ResponseEntity<HttpStatus> deleteAllClients() {
		return clientService.deleteAllClients();
	}

	//  http://localhost:8080/api/clients/confirmed
	@GetMapping("/clients/confirmed")
	public ResponseEntity<List<Client>> findByConfirmed() {
		return clientService.findByConfirmed();
	}

}