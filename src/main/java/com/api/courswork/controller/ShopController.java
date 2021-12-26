package com.api.courswork.controller;

import com.api.courswork.model.Shop;
import com.api.courswork.service.ShopService;
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
public class ShopController {
    @Autowired
    ShopService service;

    @GetMapping("/shop")
    public ResponseEntity<?> getShop() {
        Map<String, Object> jsonOut = new LinkedHashMap<>();
        try {
            List<Shop> shop = service.findAll();

            jsonOut.put("shop", shop);

            return new ResponseEntity<>(jsonOut, HttpStatus.OK);
        } catch (Exception e) {
            jsonOut.put("ErrorMessage", e.getMessage());
            return new ResponseEntity<>(jsonOut, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/shop")
    public ResponseEntity<?> getShop(@RequestBody Map<String, Object> body) {
        Map<String, Object> jsonOut = new LinkedHashMap<>();
        try {
            Shop shop = new Shop(body.get("title").toString(), body.get("description").toString(), Integer.parseInt(body.get("count").toString()), body.get("price").toString());

            service.save(shop);

            jsonOut.put("message", "Ok");
            return new ResponseEntity<>(jsonOut, HttpStatus.OK);
        } catch (Exception e) {
            jsonOut.put("ErrorMessage", e.getMessage());
            return new ResponseEntity<>(jsonOut, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
