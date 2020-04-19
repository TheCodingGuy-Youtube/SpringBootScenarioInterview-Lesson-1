package com.mapleleaf.userservice;

import com.mapleleaf.userservice.entity.User;
import com.mapleleaf.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class UserserviceApplication {


    public static void main(String[] args) {
        SpringApplication.run(UserserviceApplication.class, args);
    }

}
