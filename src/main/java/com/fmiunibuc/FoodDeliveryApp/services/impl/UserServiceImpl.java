package com.fmiunibuc.FoodDeliveryApp.services.impl;

import com.fmiunibuc.FoodDeliveryApp.entities.User;
import com.fmiunibuc.FoodDeliveryApp.exception.UserNotFoundException;
import com.fmiunibuc.FoodDeliveryApp.repositories.UserRepository;
import com.fmiunibuc.FoodDeliveryApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        } else {
            throw new UserNotFoundException(id);
        }
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
        Optional<User> userUpdated = userRepository.findById(id);
        if(userUpdated.isPresent()){
            User userUpdatedValue = userUpdated.get();
            userUpdatedValue.setUsername(user.getUsername());
            userUpdatedValue.setAddress(user.getAddress());
            userUpdatedValue.setPhonenumber(user.getPhonenumber());
            userUpdatedValue.setEmail(user.getEmail());
            userRepository.save(userUpdatedValue);
        } else {
            throw new UserNotFoundException(id);
        }
    }

    @Override
    public void removeUser(int id){
        userRepository.deleteById(id);
    };
}
