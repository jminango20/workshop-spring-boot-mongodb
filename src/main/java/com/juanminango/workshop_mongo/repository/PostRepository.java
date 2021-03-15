package com.juanminango.workshop_mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.juanminango.workshop_mongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{ //354

	
}
