package com.fmiunibuc.FoodDeliveryApp.repositories;


import com.fmiunibuc.FoodDeliveryApp.entities.Order;
import com.fmiunibuc.FoodDeliveryApp.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer> {

    List<Order> findAllByRestaurantId(int restaurantId);
    List<Order> findAllByUserId(int restaurantId);
    List<Order> findAllByDriverId(int restaurantId);
}
