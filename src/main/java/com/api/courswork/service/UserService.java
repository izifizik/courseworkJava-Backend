package com.api.courswork.service;

import com.api.courswork.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserService extends MongoRepository<User, String> {
    User findFirstById(String id);
    User findFirstByUsername(String username);
}