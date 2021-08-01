package com.stackroute.userauthservice.service;
import com.stackroute.userauthservice.model.MentorAuthenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.stackroute.userauthservice.dto.SocialProvider;
import com.stackroute.userauthservice.exception.UserAlreadyExistAuthenticationException;
import com.stackroute.userauthservice.model.User;
import com.stackroute.userauthservice.model.Membership;
import com.stackroute.userauthservice.model.UserAuth;
import com.stackroute.userauthservice.repo.UserRepository;

@Service
public class ConsumerService implements RabbitListenerConfigurer {
    private static Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    private UserService userService;
    @Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;



    @Autowired
    public ConsumerService(UserService userService) {
        this.userService = userService;
    }

    /*This method will save the Registered users  to MYSQL-DB*/
    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedMessage(User user1) throws UserAlreadyExistAuthenticationException {
        System.out.println("hello"+user1);
        UserAuth user2 = userService.findUserByEmail(user1.getEmail());
        if (user2 == null) {
            UserAuth user = new UserAuth();
            user.setName(user1.getName());
            user.setEmail(user1.getEmail());
            user.setPassword(passwordEncoder.encode(user1.getPassword()));
            String role = "ROLE_" +  user1.getRole();
            user.setRole(role);
            user.setProvider(SocialProvider.LOCAL.name());
            user.setMembership(Membership.FREE.name());
            user = userRepository.save(user);
            userRepository.flush();
        }
    }
    @RabbitListener(queues = "${spring.rabbitmq.queue1}")
    public void receivedMessages(MentorAuthenResponse mentorAuthenResponse)  {

        System.out.println("hello"+mentorAuthenResponse);
        UserAuth user2 = userService.findUserByEmail(mentorAuthenResponse.getEmail());
        if (user2 == null) {
            UserAuth user = new UserAuth();
            user.setName(mentorAuthenResponse.getName());
            user.setEmail(mentorAuthenResponse.getEmail());
            user.setPassword(passwordEncoder.encode(mentorAuthenResponse.getPassword()));
            String role = "ROLE_" +  mentorAuthenResponse.getRole();
            user.setRole(role);
//            user.setProvider(SocialProvider.LOCAL.name());
//            user.setMembership(Membership.FREE.name());
            user = userRepository.save(user);
            userRepository.flush();
        }
    }
//        MentorAuthenResponse mentorAuthenResponse1 = new MentorAuthenResponse();
//        mentorAuthenResponse1.setEmail(mentorAuthenResponse.getEmail());
//        mentorAuthenResponse1.setPassword(mentorAuthenResponse.getPassword());
//        mentorAuthenResponse1.setName(mentorAuthenResponse.getName());
//        mentorAuthenResponse1.setRole(mentorAuthenResponse.getRole());
//       userRepository.save(mentorAuthenResponse1);



    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }
}