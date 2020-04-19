package com.mapleleaf.userservice.controller;

import com.mapleleaf.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/")
    public void test(@RequestParam("userid") String userId){
        System.out.println("User Id Recieved is : "+userId);
        userService.send(userId);
        System.out.println("data sent");
    }


}
