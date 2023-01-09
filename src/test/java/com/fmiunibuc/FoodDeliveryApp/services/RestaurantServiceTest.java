package com.fmiunibuc.FoodDeliveryApp.services;

import com.fmiunibuc.FoodDeliveryApp.entities.Restaurant;
import com.fmiunibuc.FoodDeliveryApp.repositories.RestaurantRepository;
import com.fmiunibuc.FoodDeliveryApp.services.RestaurantService;
import com.fmiunibuc.FoodDeliveryApp.services.impl.RestaurantServiceImpl;
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

@RunWith(MockitoJUnitRunner.class)
public class RestaurantServiceTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RestaurantServiceImpl restaurantService;

    @Test
    public void getRestaurantListTest() {
        when(restaurantRepository.findAll()).thenReturn(Arrays.asList(new Restaurant("BurgerKing", "Strada 1", "Non-Stop")));

        List<Restaurant> result = restaurantService.getRestaurantList();

        assertEquals("BurgerKing", result.get(0).getName());
    }

    @Test
    public void getRestaurantByIdTest() {
        Restaurant restaurant = new Restaurant("BurgerKing", "Strada 1", "Non-Stop");
        int id = restaurant.getId();
        when(restaurantRepository.findById(id)).thenReturn(Optional.of(restaurant));

        Restaurant result = restaurantService.getRestaurantById(id);

        assertEquals(restaurant, result);

    }

    @Test
    public void addRestaurantTest() {
        Restaurant restaurant = new Restaurant("BurgerKing", "Strada 1", "Non-Stop");

        restaurantService.addRestaurant(restaurant);

        verify(restaurantRepository, times(1)).save(restaurant);

    }

    @Test
    public void updateRestaurantTest() {
        Restaurant restaurant = new Restaurant("BurgerKing", "Strada 1", "Non-Stop");
        Restaurant restaurantUpdated = new Restaurant("BurgerQueen", "Strada 2", "");
        int id = restaurantUpdated.getId();
        when(restaurantRepository.findById(id)).thenReturn(Optional.of(restaurant));

        restaurantService.updateRestaurant(id, restaurant);
        verify(restaurantRepository, times(1)).save(restaurant);

    }

    @Test
    public void removeRestaurantTest() {
        Restaurant restaurant = new Restaurant("BurgerKing", "Strada 1", "Non-Stop");
        int id = restaurant.getId();
        restaurantService.removeRestaurant(id);
        verify(restaurantRepository, times(1)).deleteById(id);

    }

}
