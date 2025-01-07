package com.example.IASF.controller;

import com.example.IASF.dto.req.UserReq;
import com.example.IASF.dto.res.UserRes;
import com.example.IASF.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping()
    public List<UserRes> getAllUser(){
        return userService.all();
    }
    @PostMapping()
    public ResponseEntity<UserRes> createUser(@RequestBody UserReq user){
        return ResponseEntity.ok(userService.create(user));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRes> updateUser(@PathVariable Integer id, @RequestBody UserReq userReq) {
        return ResponseEntity.ok(userService.update(id, userReq));
    }
}
