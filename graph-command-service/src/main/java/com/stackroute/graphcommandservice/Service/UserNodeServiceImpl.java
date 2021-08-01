package com.stackroute.graphcommandservice.Service;

import com.stackroute.graphcommandservice.Exception.NodeAlreadyExistsException;
import com.stackroute.graphcommandservice.Exception.NodeNotFoundException;
import com.stackroute.graphcommandservice.Model.UserNode;
import com.stackroute.graphcommandservice.Repository.UserNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserNodeServiceImpl implements UserNodeService{
    private UserNodeRepository userNodeRepository;

    @Autowired
    public UserNodeServiceImpl(UserNodeRepository userNodeRepository) {
        this.userNodeRepository = userNodeRepository;
    }

    @Override
    public UserNode saveNewNode(UserNode userNode) throws NodeAlreadyExistsException {
        if (userNodeRepository.findById(userNode.getEmail()).isPresent()){
            throw new NodeAlreadyExistsException("User Already Exists");
        }
        return userNodeRepository.save(userNode);
    }

    @Override
    public UserNode updateExistingNode(String email,String planType, int physical_level ,int mental_level, int diet_level, double physicalScore, double mentalScore, double dietScore) throws NodeNotFoundException {
        if (userNodeRepository.findById(email).isEmpty()){
            throw new NodeNotFoundException("user Not Found");
        }
        UserNode existedUser = userNodeRepository.findById(email).get();
        existedUser.setPlanType(planType);
        existedUser.setDate(new Date());
        existedUser.setPhysical_level(physical_level);
        existedUser.setMental_level(mental_level);
        existedUser.setDiet_level(diet_level);
        existedUser.setPhysicalScore(physicalScore);
        existedUser.setMentalScore(mentalScore);
        existedUser.setDietScore(dietScore);
        return userNodeRepository.save(existedUser);
    }

    @Override
    public UserNode deleteExistingNode(String name) {
        return null;
    }

    @Override
    public List<UserNode> fetchAllNodes() {
        return null;
    }

    @Override
    public UserNode fetchNodeWithName(String name) {
        return null;
    }
}
