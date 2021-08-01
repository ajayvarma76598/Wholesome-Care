package com.stackroute.userauthservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.stackroute.userauthservice.model.UserAuth;

@RepositoryRestResource(collectionResourceRel = "users",path = "users")
public interface UserRepository extends JpaRepository<UserAuth, Long> {

	UserAuth findByEmail(String email);

	boolean existsByEmail(String email);

}
