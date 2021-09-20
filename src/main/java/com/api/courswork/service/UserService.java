package com.api.courswork.service;

import com.api.courswork.model.User;

import java.util.List;

public interface UserService {
    void insertUser(User user);

    User findById(int Id);

    boolean updateUserById(User user, int Id);

    boolean deleteUserById(int Id);
}
