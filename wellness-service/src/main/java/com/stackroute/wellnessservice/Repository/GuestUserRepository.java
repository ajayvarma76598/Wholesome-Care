package com.stackroute.wellnessservice.Repository;

import com.stackroute.wellnessservice.model.GuestUser;
import com.stackroute.wellnessservice.model.Helpline;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(collectionResourceRel = "guestuser",path = "guestuser")
public interface GuestUserRepository extends MongoRepository<GuestUser,String> {
    GuestUser getUserByEmail(String email);
}
