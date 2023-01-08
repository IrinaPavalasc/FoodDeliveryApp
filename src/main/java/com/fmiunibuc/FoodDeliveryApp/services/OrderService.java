package com.fmiunibuc.FoodDeliveryApp.services;

import com.fmiunibuc.FoodDeliveryApp.entities.Order;
import com.fmiunibuc.FoodDeliveryApp.entities.Product;

import java.util.List;

public interface OrderService {

    List<Order> getOrdersByUserId(int userId);

    List<Order> getOrdersByRestaurantId(int restaurantId);

    List<Order> getOrdersByDriverId(int restaurantId);

    List<Product> getProductsByOrderId(int id);

    Order getOrderById(int id);

    Order addOrder(Order order, int restaurantId, int userId, int driverId);

    Order addProductToOrder(int id, int productId);

    Order removeProductFromOrder(int id, int productId);

    void updateOrder(int id, Order order);

    void removeOrder(int id);
}
