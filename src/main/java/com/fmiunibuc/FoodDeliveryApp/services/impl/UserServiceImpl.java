package com.fmiunibuc.FoodDeliveryApp.services.impl;

import com.fmiunibuc.FoodDeliveryApp.entities.User;
import com.fmiunibuc.FoodDeliveryApp.repositories.UserRepository;
import com.fmiunibuc.FoodDeliveryApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getUserList() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void updateUser(int id, User user){
        User userUpdated = userRepository.findById(id).get();
        userUpdated.setUsername(user.getUsername());
        userUpdated.setAddress(user.getAddress());
        userUpdated.setPhonenumber(user.getPhonenumber());
        userUpdated.setEmail(user.getEmail());
        userRepository.save(userUpdated);
    }

    @Override
    public void removeUser(int id){
        userRepository.deleteById(id);
    };
}
