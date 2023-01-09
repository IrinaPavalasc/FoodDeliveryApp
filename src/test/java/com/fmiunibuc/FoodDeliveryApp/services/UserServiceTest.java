package com.fmiunibuc.FoodDeliveryApp.services;

import com.fmiunibuc.FoodDeliveryApp.entities.User;
import com.fmiunibuc.FoodDeliveryApp.repositories.UserRepository;
import com.fmiunibuc.FoodDeliveryApp.services.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void getUserListTest() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(new User("Jane", "Strada 1", "0740234564", "jane@gmail.com")));

        List<User> result = userService.getUserList();

        assertEquals("Jane", result.get(0).getUsername());
    }

    @Test
    public void getUserByIdTest() {
        User user = new User("Jane", "Strada 1", "0740234564", "jane@gmail.com");
        int id = user.getId();
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        User result = userService.getUserById(id);

        assertEquals(user, result);

    }

    @Test
    public void addUserTest() {
        User user = new User("Jane", "Strada 1", "0740234564", "jane@gmail.com");

        userService.addUser(user);

        verify(userRepository, times(1)).save(user);

    }

    @Test
    public void updateUserTest() {
        User user = new User("Jane", "Strada 1", "0740234564", "jane@gmail.com");
        User userUpdated = new User("John", "Strada 2", "0740234564", "john@gmail.com");
        int id = userUpdated.getId();
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        userService.updateUser(id, user);

        verify(userRepository, times(1)).save(user);

    }

    @Test
    public void removeUserTest() {
        User user = new  User("Jane", "Strada 1", "0740234564", "jane@gmail.com");
        int id = user.getId();

        userService.removeUser(id);

        verify(userRepository, times(1)).deleteById(id);

    }

}