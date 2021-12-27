package com.api.courswork.controller;

import com.api.courswork.model.Shop;
import com.api.courswork.model.Storage;
import com.api.courswork.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping(value = "/api/v1")
public class StorageController {
    @Autowired
    StorageService service;

    @GetMapping("/storage")
    public ResponseEntity<?> getShop() {
        Map<String, Object> jsonOut = new LinkedHashMap<>();
        try {
            List<Storage> storage = service.findAll();

            jsonOut.put("storage", storage);

            return new ResponseEntity<>(jsonOut, HttpStatus.OK);
        } catch (Exception e) {
            jsonOut.put("ErrorMessage", e.getMessage());
            return new ResponseEntity<>(jsonOut, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/storage")
    public ResponseEntity<?> getShop(@RequestBody Map<String, Object> body) {
        Map<String, Object> jsonOut = new LinkedHashMap<>();
        try {
            Storage storage = new Storage(body.get("title").toString(), body.get("description").toString(), Integer.parseInt(body.get("count").toString()));

            service.save(storage);

            jsonOut.put("message", "Ok");
            return new ResponseEntity<>(jsonOut, HttpStatus.OK);
        } catch (Exception e) {
            jsonOut.put("ErrorMessage", e.getMessage());
            return new ResponseEntity<>(jsonOut, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
