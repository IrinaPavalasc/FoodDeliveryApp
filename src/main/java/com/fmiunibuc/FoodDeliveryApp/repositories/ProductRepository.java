package com.fmiunibuc.FoodDeliveryApp.repositories;

import com.fmiunibuc.FoodDeliveryApp.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    List<Product> findAllByRestaurantId(int restaurantId);
}
