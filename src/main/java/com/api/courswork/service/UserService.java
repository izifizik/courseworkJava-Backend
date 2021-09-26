package com.api.courswork.service;

import com.api.courswork.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserService extends MongoRepository<User, Integer> {
    User findFirstById(String id);
    User findFirstByUsername(String username);
}