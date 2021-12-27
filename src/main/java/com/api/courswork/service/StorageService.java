package com.api.courswork.service;

import com.api.courswork.model.Storage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageService extends MongoRepository<Storage, String> {
}
