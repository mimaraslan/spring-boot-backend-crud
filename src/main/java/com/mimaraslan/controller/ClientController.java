package com.mimaraslan.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mimaraslan.model.Client;
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

import com.mimaraslan.repository.ClientRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ClientController {

	@Autowired
	ClientRepository clientRepository;

	@GetMapping("/clients")
	public ResponseEntity<List<Client>> getAllClients(@RequestParam(required = false) String firstName) {
		try {
			List<Client> clients = new ArrayList<Client>();

			if (firstName == null)
				clientRepository.findAll().forEach(clients::add);
			else
				clientRepository.findByFirstNameContaining(firstName).forEach(clients::add);

			if (clients.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(clients, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/clients/{id}")
	public ResponseEntity<Client> getClientById(@PathVariable("id") Integer id) {
		Optional<Client> clientData = clientRepository.findById(id);

		if (clientData.isPresent()) {
			return new ResponseEntity<>(clientData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/clients")
	public ResponseEntity<Client> createClient(@RequestBody Client client) {
		try {
			Client clientObj = clientRepository
					.save(new Client(client.getFirstName(), client.getLastName(), false));
			return new ResponseEntity<>(clientObj, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/clients/{id}")
	public ResponseEntity<Client> updateClient(@PathVariable("id") Integer id, @RequestBody Client client) {
		Optional<Client> clientData = clientRepository.findById(id);

		if (clientData.isPresent()) {
			Client clientObj = clientData.get();
			clientObj.setFirstName(client.getFirstName());
			clientObj.setLastName(client.getLastName());
			clientObj.setConfirmed(client.isConfirmed());
			return new ResponseEntity<>(clientRepository.save(clientObj), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/clients/{id}")
	public ResponseEntity<HttpStatus> deleteClient(@PathVariable("id") Integer id) {
		try {
			clientRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/clients")
	public ResponseEntity<HttpStatus> deleteAllClients() {
		try {
			clientRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/clients/confirmed")
	public ResponseEntity<List<Client>> findByConfirmed() {
		try {
			List<Client> clients = clientRepository.findByConfirmed(true);

			if (clients.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(clients, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
