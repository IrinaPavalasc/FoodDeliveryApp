package com.fmiunibuc.FoodDeliveryApp.services.impl;

import com.fmiunibuc.FoodDeliveryApp.entities.*;
import com.fmiunibuc.FoodDeliveryApp.exception.*;
import com.fmiunibuc.FoodDeliveryApp.repositories.*;
import com.fmiunibuc.FoodDeliveryApp.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent()){
            return order.get().getProducts();
        } else {
            throw new OrderNotFoundException();
        }
    }

    @Override
    public Order getOrderById(int id){
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent()){
            return order.get();
        } else {
            throw new OrderNotFoundException();
        }
    }
    @Override
    public Order addOrder(Order order, int restaurantId, int userId, int driverId){

        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        Optional<User> user = userRepository.findById(userId);
        Optional<Driver> driver = driverRepository.findById(driverId);
        if(restaurant.isEmpty()){
            throw new RestaurantNotFoundException(restaurantId);
        }
        else if(user.isEmpty()){
            throw new UserNotFoundException(userId);
        }
        else if(driver.isEmpty()){
            throw new DriverNotFoundException();
        }
        else {
            order.setRestaurant(restaurant.get());
            order.setUser(user.get());
            order.setDriver(driver.get());
            return orderRepository.save(order);
        }
    }

    @Override
    public Order addProductToOrder(int id, int productId){
        Optional<Order> order = orderRepository.findById(id);
        Optional<Product> product = productRepository.findById(id);
        if(order.isEmpty()){
            throw new OrderNotFoundException();
        }
        else if(product.isEmpty()){
            throw new ProductNotFoundException();
        }
        else {
            Order orderValue = order.get();
            Product productValue = product.get();
            orderValue.getProducts().add(productValue);
            return orderRepository.save(orderValue);
        }
    }
    @Override
    public Order removeProductFromOrder(int id, int productId){
        Optional<Order> order = orderRepository.findById(id);
        Optional<Product> product = productRepository.findById(id);
        if(order.isEmpty()){
            throw new OrderNotFoundException();
        }
        else if(product.isEmpty()){
            throw new ProductNotFoundException();
        }
        else {
            Order orderValue = order.get();
            Product productValue = product.get();
            orderValue.getProducts().remove(productValue);
            return orderRepository.save(orderValue);
        }

    }
    @Override
    public void updateOrder(int id, Order order){
        Optional<Order> orderUpdated = orderRepository.findById(id);
        if(orderUpdated.isPresent()){
            Order orderUpdatedValue = orderUpdated.get();
            orderUpdatedValue.setPredictedDeliveryTime(order.getPredictedDeliveryTime());
            orderUpdatedValue.setStatus(order.getStatus());
            orderRepository.save(orderUpdatedValue);
        } else {
            throw new OrderNotFoundException();
        }

    }
    @Override
    public void removeOrder(int id){
        orderRepository.deleteById(id);
    };
}
