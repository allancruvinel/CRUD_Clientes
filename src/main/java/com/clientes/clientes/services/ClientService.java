package com.clientes.clientes.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clientes.clientes.dto.ClientDTO;
import com.clientes.clientes.entities.Client;
import com.clientes.clientes.repository.ClientRepository;
import com.clientes.clientes.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {
	@Autowired
	private ClientRepository repository;
	
	@Transactional
	public ClientDTO getById(Long id) {
		Optional<Client> obj = repository.findById(id);

		Client client = obj.orElseThrow(() -> new ResourceNotFoundException("object not found"));
		return new ClientDTO(client);
	}

}
