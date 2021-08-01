package com.stackroute.graphcommandservice.Service;

import com.stackroute.graphcommandservice.Exception.NodeAlreadyExistsException;
import com.stackroute.graphcommandservice.Exception.NodeNotFoundException;
import com.stackroute.graphcommandservice.Model.UserNode;

import java.util.List;

public interface UserNodeService {

    public UserNode saveNewNode(UserNode userNode) throws NodeAlreadyExistsException;
    public UserNode updateExistingNode(String email,String planType, int physical_level ,int mental_level, int diet_level, double physicalScore, double mentalScore, double dietScore) throws NodeNotFoundException;
    public UserNode deleteExistingNode(String name);
    public List<UserNode> fetchAllNodes();
    public UserNode fetchNodeWithName(String name);
}
