package com.api.courswork.controller;

import com.api.courswork.model.User;
import com.api.courswork.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping(value = "/api/v1")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") String id) {
        Map<String, Object> jsonOut = new LinkedHashMap<>();
        try {
            User user = service.findFirstById(id);

            if (user == null) {
                jsonOut.put("message", "NOT_FOUND");
                return new ResponseEntity<>(jsonOut, HttpStatus.NOT_FOUND);
            }

            jsonOut.put("id", user.getId());
            jsonOut.put("username", user.getUsername());
            jsonOut.put("admin", user.getAdmin());
            jsonOut.put("message", "Ok");

            return new ResponseEntity<>(jsonOut, HttpStatus.OK);
        } catch (Exception e) {
            jsonOut.put("ErrorMessage", e.getMessage());
            return new ResponseEntity<>(jsonOut, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {
        Map<String, Object> jsonOut = new LinkedHashMap<>();
        try {
            User user = service.findFirstById(id);

            service.delete(user);

            jsonOut.put("ErrorMessage", null);
            return new ResponseEntity<>(jsonOut, HttpStatus.OK);
        } catch (Exception e) {
            jsonOut.put("ErrorMessage", e.getMessage());
            return new ResponseEntity<>(jsonOut, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user/register")
    public ResponseEntity<?> createUser(@RequestBody Map<String, Object> body) {
        Map<String, Object> jsonOut = new LinkedHashMap<>();
        try {
            User user = new User(body.get("username").toString(), hashPassword(body.get("password").toString()), false);

            service.save(user);

            jsonOut.put("id", user.getId());
            jsonOut.put("message", "OK");
            return new ResponseEntity<>(jsonOut, HttpStatus.OK);
        } catch (Exception e) {
            jsonOut.put("ErrorMessage", e.getMessage());
            return new ResponseEntity<>(jsonOut, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user/auth")
    public ResponseEntity<?> authUser(@RequestBody User user) {
        Map<String, Object> jsonOut = new LinkedHashMap<>();
        try {
            User authUser = service.findFirstByUsername(user.getUsername());

            if (authUser == null) {
                jsonOut.put("message", "NOT_FOUND");
                return new ResponseEntity<>(jsonOut, HttpStatus.NOT_FOUND);
            }

            if (checkPassword(user.getPassword(), authUser.getPassword())) {
                jsonOut.put("message", "Incorrect password");
                return new ResponseEntity<>(jsonOut, HttpStatus.BAD_REQUEST);
            }

            jsonOut.put("id", authUser.getId());
            jsonOut.put("message", "OK");
            return new ResponseEntity<>(jsonOut, HttpStatus.OK);
        } catch (Exception e) {
            jsonOut.put("ErrorMessage", e.getMessage());
            return new ResponseEntity<>(jsonOut, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method can be used to generate a string representing an account password
     * suitable for storing in a database. It will be an OpenBSD-style crypt(3) formatted
     * hash string of length=60
     * The bcrypt workload is specified in the above static variable, a value from 10 to 31.
     * A workload of 12 is a very reasonable safe default as of 2013.
     * This automatically handles secure 128-bit salt generation and storage within the hash.
     *
     * @param password_plaintext The account's plaintext password as provided during account creation,
     *                           or when changing an account's password.
     * @return String - a string of length 60 that is the bcrypt hashed password in crypt(3) format.
     */
    public static String hashPassword(String password_plaintext) {
        String salt = BCrypt.gensalt(12);

        return BCrypt.hashpw(password_plaintext, salt);
    }

    /**
     * This method can be used to verify a computed hash from a plaintext (e.g. during a login
     * request) with that of a stored hash from a database. The password hash from the database
     * must be passed as the second variable.
     *
     * @param password_plaintext The account's plaintext password, as provided during a login request
     * @param stored_hash        The account's stored password hash, retrieved from the authorization database
     * @return boolean - true if the password matches the password of the stored hash, false otherwise
     */
    public static boolean checkPassword(String password_plaintext, String stored_hash) {
        boolean result = false;

        if (null == stored_hash || !stored_hash.startsWith("$2a$"))
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

        result = BCrypt.checkpw(password_plaintext, stored_hash);

        return !result;
    }
}
