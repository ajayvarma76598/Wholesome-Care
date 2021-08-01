package com.stackroute.userservice.repository;

import com.stackroute.userservice.model.User;
import com.stackroute.userservice.model.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "users",path = "users")
public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findByEmail(String email);

}
