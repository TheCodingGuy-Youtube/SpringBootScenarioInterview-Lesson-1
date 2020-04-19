package com.mapleleaf.userservice.repository;

import com.mapleleaf.userservice.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String>{

    User findByEmail(String email);
}
