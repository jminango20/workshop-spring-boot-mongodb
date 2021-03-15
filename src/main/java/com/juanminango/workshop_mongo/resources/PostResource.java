package com.juanminango.workshop_mongo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.juanminango.workshop_mongo.domain.Post;
import com.juanminango.workshop_mongo.services.PostService;

@RestController
@RequestMapping(value="/posts")
public class PostResource { //O Controllador Rest accesa ao Service e o service accesa por sua vez ao repository

	@Autowired
	private PostService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String id){
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
		
	
}
