package com.juanminango.workshop_mongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juanminango.workshop_mongo.domain.User;
import com.juanminango.workshop_mongo.repository.UserRepository;
import com.juanminango.workshop_mongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo; //Injecao de depencencia automatica
	
	public List<User> findAll(){ //retorna todos os usuarios do meu banco
		return repo.findAll();		
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto nao encontrado"));
	}
}
