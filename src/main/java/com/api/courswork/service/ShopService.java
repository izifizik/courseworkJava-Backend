package com.api.courswork.service;

import com.api.courswork.model.Shop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopService extends MongoRepository<Shop, String> {
}
