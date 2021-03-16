package com.juanminango.workshop_mongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.juanminango.workshop_mongo.domain.Post;
import com.juanminango.workshop_mongo.resources.util.URL;
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
	
	//360
	@RequestMapping(value = "/titlesearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text",defaultValue = "") String text){
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}	
	
	//362
	@RequestMapping(value = "/fullsearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> fullSearch(
			@RequestParam(value="text",defaultValue = "") String text,
			@RequestParam(value="minDate",defaultValue = "") String minDate,
			@RequestParam(value="maxDate",defaultValue = "") String maxDate){
		text = URL.decodeParam(text); //decodificar o texto
		Date min = URL.convertDate(minDate, new Date(0L)); //0L -> 1970
		Date max = URL.convertDate(maxDate, new Date()); //data de hoje
		List<Post> list = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);
	}
	
}
