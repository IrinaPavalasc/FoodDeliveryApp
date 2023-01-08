package com.fmiunibuc.FoodDeliveryApp.services.impl;

import com.fmiunibuc.FoodDeliveryApp.entities.*;
import com.fmiunibuc.FoodDeliveryApp.repositories.*;
import com.fmiunibuc.FoodDeliveryApp.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Order> getOrdersByRestaurantId(int restaurantId){
        return orderRepository.findAllByRestaurantId(restaurantId);
    }

    @Override
    public List<Order> getOrdersByUserId(int userId){
        return orderRepository.findAllByUserId(userId);
    }
    @Override
    public List<Order> getOrdersByDriverId(int driverId){
        return orderRepository.findAllByDriverId(driverId);
    }

    @Override
    public List<Product> getProductsByOrderId(int id){
        Order order = orderRepository.findById(id).get();
        return order.getProducts();
    }

    @Override
    public Order getOrderById(int id){
        return orderRepository.findById(id).get();
    }
    @Override
    public Order addOrder(Order order, int restaurantId, int userId, int driverId){
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
        User user = userRepository.findById(userId).get();
        Driver driver = driverRepository.findById(driverId).get();
        order.setRestaurant(restaurant);
        order.setUser(user);
        order.setDriver(driver);
        return orderRepository.save(order);
    }

    @Override
    public Order addProductToOrder(int id, int productId){
        Order order = orderRepository.findById(id).get();
        Product product = productRepository.findById(productId).get();
        order.getProducts().add(product);
        return orderRepository.save(order);
    }
    @Override
    public Order removeProductFromOrder(int id, int productId){
        Order order = orderRepository.findById(id).get();
        Product product = productRepository.findById(productId).get();
        order.getProducts().remove(product);
        return orderRepository.save(order);
    }
    @Override
    public void updateOrder(int id, Order order){
        Order orderUpdated = orderRepository.findById(id).get();
        orderUpdated.setPredictedDeliveryTime(order.getPredictedDeliveryTime());
        orderUpdated.setStatus(order.getStatus());
        orderRepository.save(orderUpdated);
    }
    @Override
    public void removeOrder(int id){
        orderRepository.deleteById(id);
    };
}
