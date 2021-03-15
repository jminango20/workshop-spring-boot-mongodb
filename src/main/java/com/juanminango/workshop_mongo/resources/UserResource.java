package com.juanminango.workshop_mongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.juanminango.workshop_mongo.domain.User;
import com.juanminango.workshop_mongo.dto.UserDTO;
import com.juanminango.workshop_mongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource { //O Controllador Rest accesa ao Service e o service accesa por sua vez ao repository

	@Autowired
	private UserService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll(){
		//List<User> list = new ArrayList<>(); //List eh interface, devemos implementar uma instanciacao da interface
		List<User> list = service.findAll();
		List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	//350
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDTO){
		User obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		//Cabecalho do inserido
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build(); //resposta vazia, com codigo 201 e com cabecalho contendo a localizacao do novo recurso criado
	}
	
	//351
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<UserDTO> delete(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
