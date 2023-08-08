package com.demo.DemoApp.data.repository;

import com.demo.DemoApp.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, String> {
    User findUserByEmail(String email);
}
