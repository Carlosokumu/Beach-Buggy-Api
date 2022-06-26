package com.restapi.springbootrestapi.repositories;


import com.restapi.springbootrestapi.models.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhotoRepository extends MongoRepository<Photo, String> {

}
