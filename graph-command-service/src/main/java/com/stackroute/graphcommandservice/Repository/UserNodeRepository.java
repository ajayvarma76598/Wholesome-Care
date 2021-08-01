package com.stackroute.graphcommandservice.Repository;

import com.stackroute.graphcommandservice.Model.User;
import com.stackroute.graphcommandservice.Model.UserNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserNodeRepository extends Neo4jRepository<UserNode, String> {
}
