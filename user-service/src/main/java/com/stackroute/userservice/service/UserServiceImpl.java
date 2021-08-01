package com.stackroute.userservice.service;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.model.UserProfile;
import com.stackroute.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {
        Optional<User> userExists = userRepository.findById(user.getEmail());
        if(userExists.isPresent()) {
            throw new UserAlreadyExistsException("UserAlreadyExistException");
        }
        user.setRole("USER");
        user.setImageUrl("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.iconfinder.com%2Ficons%2F7084331%2Fuser_profile_avatar_people_man_icon&psig=AOvVaw2KzdcIj496RN89y1UuOxVR&ust=1626241546630000&source=images&cd=vfe&ved=0CAoQjRxqFwoTCMixvIis3_ECFQAAAAAdAAAAABAF");
        return userRepository.save(user);
    }

    @Override
    public User getUserByEmailId(String email) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(email);
        if(!user.isPresent()){
            throw new UserNotFoundException("UserNotFoundException");
        }
        return user.get();
    }

    @Override
    public User updateUserByEmailId(User user) throws UserNotFoundException {
    	User updateUser=userRepository.findById(user.getEmail()).get();
        if(updateUser==null){
            throw new UserNotFoundException("UserNotFoundException");

        }

       return userRepository.save(user);
    }
}







