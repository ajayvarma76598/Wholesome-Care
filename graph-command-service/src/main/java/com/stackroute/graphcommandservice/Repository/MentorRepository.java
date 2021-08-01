package com.stackroute.graphcommandservice.Repository;

import com.stackroute.graphcommandservice.Model.Mentor;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface MentorRepository extends Neo4jRepository<Mentor, String > {
}
