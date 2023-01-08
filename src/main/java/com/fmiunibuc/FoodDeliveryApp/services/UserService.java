package com.fmiunibuc.FoodDeliveryApp.services;
import com.fmiunibuc.FoodDeliveryApp.entities.User;

import java.util.List;

public interface UserService {

    User getUserById(int id);

    List<User> getUserList();

    User addUser(User user);

    void updateUser(int id, User user);

    void removeUser(int id);
}
