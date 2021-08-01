package com.stackroute.wellnessservice.Repository;

import com.stackroute.wellnessservice.model.Helpline;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(collectionResourceRel = "helpline",path = "helpline")
public interface HelplineRepository extends MongoRepository<Helpline,String> {
    Helpline getHelplineByCityName(String cityName);
}
