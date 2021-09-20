package com.api.courswork.service;

import com.api.courswork.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserServiceImpl implements UserService {
    //User storage
    private static final Map<Integer, User> USER_MAP = new HashMap<>();
    // variable to generate rand Id for user
    private static final AtomicInteger USER_ID_HOLDER = new AtomicInteger();

    @Override
    public String user() {
        return "Ya rabotay";
    }

    @Override
    public void insertUser(User user) {
        final int userId = USER_ID_HOLDER.incrementAndGet();
        user.setId(userId);
        USER_MAP.put(userId, user);
    }

    @Override
    public User findById(int id) {
        return USER_MAP.get(id);
    }

    @Override
    public boolean updateUserById(User user, int id) {
        if (USER_MAP.containsKey(id)) {
            USER_MAP.put(id, user);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUserById(int id) {
        return USER_MAP.remove(id) != null;
    }
}
