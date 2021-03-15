package com.juanminango.workshop_mongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.juanminango.workshop_mongo.domain.User;
import com.juanminango.workshop_mongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		userRepository.deleteAll(); //limpa a colecao no Mongo DB
		//Instancia minha colecao
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		//Salvo no DB
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
	}

}
