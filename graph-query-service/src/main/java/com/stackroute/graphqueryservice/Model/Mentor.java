package com.stackroute.graphqueryservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Node
public class Mentor {
    @Id
    private String emailId;
    private String name;
    private String expertize;
}
