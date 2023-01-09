package com.fmiunibuc.FoodDeliveryApp.controllers;

import com.fmiunibuc.FoodDeliveryApp.entities.Restaurant;
import com.fmiunibuc.FoodDeliveryApp.entities.User;
import com.fmiunibuc.FoodDeliveryApp.services.RestaurantService;
import com.fmiunibuc.FoodDeliveryApp.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "User Controller", description = "Manage Users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Get User")
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id){
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(summary = "Get All Users")
    @GetMapping("/user/all")
    public ResponseEntity<List<User>> getUserList(){
        List<User> userList = (List<User>) userService.getUserList();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @Operation(summary = "Update User")
    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody @Valid User user){
        userService.updateUser(id, user);
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @Operation(summary = "Add User")
    @PostMapping("/user/add")
    public ResponseEntity<User> addUser(@RequestBody @Valid User user){
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete User")
    @DeleteMapping("/user/{id}")
    public ResponseEntity<HttpStatus> removeUser(@PathVariable int id){
        userService.removeUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
