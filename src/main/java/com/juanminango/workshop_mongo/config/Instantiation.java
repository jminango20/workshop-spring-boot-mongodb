package com.juanminango.workshop_mongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.juanminango.workshop_mongo.domain.Post;
import com.juanminango.workshop_mongo.domain.User;
import com.juanminango.workshop_mongo.repository.PostRepository;
import com.juanminango.workshop_mongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		// TODO Auto-generated method stub
		userRepository.deleteAll(); //limpa a colecao no Mongo DB
		postRepository.deleteAll();
		
		//Instancia minha colecao
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		//Salvo no DB
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		//354 Instanciar Post
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para Sao Paulo. Abracos!",maria);
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!",maria);
		postRepository.saveAll(Arrays.asList(post1,post2));
		
	}

}
