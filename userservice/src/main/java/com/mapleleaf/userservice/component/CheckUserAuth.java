package com.mapleleaf.userservice.component;

import com.mapleleaf.userservice.entity.User;
import com.mapleleaf.userservice.repository.UserRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Consumer;

@Component
public class CheckUserAuth {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private AmqpTemplate rabbitTemplate;


    @RabbitListener(queues = "${mapleleaf.authQueue}")
    public void recieveUserId(String userId) {

        System.out.println("Recieved User ID : " + userId);
        Optional<User> userData = userRepository.findById(userId);
        User objResponse = new User();
        userData.ifPresent(new Consumer<User>() {
            @Override
            public void accept(User user) {
                objResponse.setActive(user.isActive());
                objResponse.setCreatedDate(user.getCreatedDate());
                objResponse.setEmail(user.getEmail());
            }
        });
        System.out.println("Sending Response Back -- "+objResponse.toString());
        rabbitTemplate.convertAndSend("AuthResponse",objResponse.toString());

    }


}
