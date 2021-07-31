package com.clientes.clientes.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clientes.clientes.dto.ClientDTO;
import com.clientes.clientes.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {
	
	@Autowired
	private ClientService clientService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> getById(@PathVariable Long id){
		ClientDTO client = clientService.getById(id);
		return ResponseEntity.ok(client);
	}
	
	
	@PostMapping()
	public ResponseEntity<ClientDTO> getAll(@RequestBody ClientDTO item){
		return ResponseEntity.ok().body(clientService.insert(item));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> update(@PathVariable Long id,@RequestBody ClientDTO item){
		item = clientService.update(id,item);
		return ResponseEntity.ok(item);
	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete (@PathVariable Long id) {
		clientService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping()
	public ResponseEntity<Page<ClientDTO>> getAll(
			@RequestParam(value = "linesPerPage", defaultValue = "10") int pageSize,
			@RequestParam(value = "page",defaultValue = "0") int pageNumber,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction
			){
		PageRequest pg = PageRequest.of(pageNumber, pageSize,Direction.valueOf(direction), orderBy);
		return ResponseEntity.ok().body(clientService.findAllPaged(pg));
	}
}
