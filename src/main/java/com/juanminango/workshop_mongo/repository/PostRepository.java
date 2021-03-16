package com.juanminango.workshop_mongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.juanminango.workshop_mongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{ //354

	List<Post> findByTitleContainingIgnoreCase(String text);
	
	//361
	@Query("{ 'title' : {$regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String text);
	
	//362
	@Query("{ $and: [ { date: {$gte: ?1} }, {date: { $lte: ?2}} , { $or: [ {'title':{$regex: ?0, $options: 'i'}}, {'body':{$regex: ?0, $options: 'i'}}, {'comments.text':{$regex: ?0, $options: 'i'}} ] } ] }") //?1 eh minDate
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
	
}
