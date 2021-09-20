package com.api.courswork.controller;

import com.api.courswork.model.User;
import com.api.courswork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Struct;
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/api/user")
    public ResponseEntity<?> create(@RequestBody User user) {
        userService.insertUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/api/user/{id}")
    public ResponseEntity<User> findById(@PathVariable(name = "id") int id) {
        final User user = userService.findById(id);

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping(value = "/api/user")
    public ResponseEntity<String> user() {
        final String req = userService.user();

        return new ResponseEntity<>(req, HttpStatus.OK);
    }

    @PutMapping(value = "/api/user/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable(name = "id") int id, @RequestBody User user) {
        final boolean update = userService.updateUserById(user, id);

        if (update) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/api/user/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable(name = "id") int id) {
        final boolean delete = userService.deleteUserById(id);

        if (delete) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
