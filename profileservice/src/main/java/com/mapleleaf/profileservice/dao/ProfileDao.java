package com.mapleleaf.profileservice.dao;

import com.mapleleaf.profileservice.entity.Profile;
import com.mapleleaf.profileservice.repository.ProfileRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class ProfileDao {

    @Autowired
    ProfileRepository profileRepository;

    private String data = null;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${mapleleaf.exchange}")
    private String exchange;

    @Value("${mapleleaf.routingkey}")
    private String routingkey;

    /*
    This method is used to send User Id to user Service
     */
    public void send(String message) {
        rabbitTemplate.convertAndSend(exchange, routingkey, message);
        System.out.println("Sendig User ID msg = " + message);
    }

    @RabbitListener(queues = "AuthResponse")
    public synchronized void setDataForAuth(String msg) {
        System.out.println("Recieved Response ::" + msg);
        data = msg;
    }

    /*
    This method is called by service layer
     */
    public Profile fetchProfileData(String userId) {
        send(userId);
        Profile objProfile = null;
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (data != null) {
            objProfile = profileRepository.findByUserId(userId); //Now hitting to mysql db for profile
            System.out.println("Inside data present");
        } else {
            System.out.println("data null inside");
        }
        data = null;
        return objProfile;

    }


}
