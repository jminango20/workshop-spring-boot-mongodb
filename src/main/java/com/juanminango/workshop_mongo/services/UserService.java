package com.juanminango.workshop_mongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juanminango.workshop_mongo.domain.User;
import com.juanminango.workshop_mongo.dto.UserDTO;
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
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	//351
	public void delete(String id) {
		findById(id); //faz uma busca primeiro, se nao encontra lanza uma excepcao.
		repo.deleteById(id);
	}
	
	//352
	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateData(User newObj, User obj) {
		// TODO Auto-generated method stub
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	//350
	public User fromDTO(UserDTO objDTO) {
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
	}
}
