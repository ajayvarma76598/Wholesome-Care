package com.stackroute.graphcommandservice.Model;

import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;


@RelationshipEntity(type = "part_of")
public class Relationship {
    @Id
    @GeneratedValue
    private long id;
    @Property
    private String level;
    @Property
    private float confidenceScore;
}