package com.clientes.clientes.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
	
	@Transactional
	public List<ClientDTO> getAll() {
		
		List<Client> items = repository.findAll();
		return items.stream().map(x-> new ClientDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional
	public Page<ClientDTO> findAllPaged(PageRequest pg) {
		return repository.findAll(pg).map(x-> new ClientDTO(x));
	}
	
	@Transactional
	public ClientDTO insert(ClientDTO item) {
		Client entity = new Client();
		entity = convertDtoToEntity(item,entity);
		return new ClientDTO(repository.save(entity));
	}
	
	@Transactional
	public ClientDTO update(Long id, ClientDTO item) {
		try {
		Client cliente = repository.getOne(id);
		cliente = convertDtoToEntity(item,cliente);
		return new ClientDTO(repository.save(cliente));
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("entidade não encontrada");
		}
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("item com id "+id+" não encontrado");
		}
		
		
	}

	private Client convertDtoToEntity(ClientDTO item, Client entity) {
		entity.setName(item.getName());
		entity.setCpf(item.getCpf());
		entity.setIncome(item.getIncome());
		entity.setChildren(item.getChildren());
		entity.setBirthDate(item.getBirthDate());
		return entity;
	}

	

	

}
