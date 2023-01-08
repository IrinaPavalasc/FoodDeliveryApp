package com.fmiunibuc.FoodDeliveryApp.repositories;

import com.fmiunibuc.FoodDeliveryApp.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
