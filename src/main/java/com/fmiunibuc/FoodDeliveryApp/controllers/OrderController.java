package com.fmiunibuc.FoodDeliveryApp.controllers;

import com.fmiunibuc.FoodDeliveryApp.entities.Order;
import com.fmiunibuc.FoodDeliveryApp.entities.Product;
import com.fmiunibuc.FoodDeliveryApp.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order/restaurant/{restaurantId}")
    public ResponseEntity<List<Order>> getOrdersByRestaurantId(@PathVariable int restaurantId){
        List<Order> orderList = (List<Order>) orderService.getOrdersByRestaurantId(restaurantId);
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }
    @GetMapping("/order/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable int userId){
        List<Order> orderList = (List<Order>) orderService.getOrdersByUserId(userId);
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }
    @GetMapping("/order/driver/{driverId}")
    public ResponseEntity<List<Order>> getOrdersByDriverId(@PathVariable int driverId){
        List<Order> orderList = (List<Order>) orderService.getOrdersByDriverId(driverId);
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }
    @GetMapping("/order/{id}/products")
    public ResponseEntity<List<Product>> getProductsByOrderId(@PathVariable int id){
        List<Product> productList = (List<Product>) orderService.getProductsByOrderId(id);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }
    @PostMapping("/order/add/{restaurantId}/{userId}/{driverId}")
    public ResponseEntity<Order> addOrder(@RequestBody @Valid Order order, @PathVariable int restaurantId, @PathVariable int userId, @PathVariable int driverId){
        return new ResponseEntity<>(orderService.addOrder(order, restaurantId, userId, driverId), HttpStatus.CREATED);
    }

    @PutMapping("/order/add/{id}/{productId}")
    public ResponseEntity<Order> addProductToOrder(@PathVariable int id, @PathVariable int productId){
        return new ResponseEntity<>(orderService.addProductToOrder(id, productId), HttpStatus.OK);
    }

    @PutMapping("/order/remove/{id}/{productId}")
    public ResponseEntity<Order> removeProductFromOrder(@PathVariable int id, @PathVariable int productId){
        return new ResponseEntity<>(orderService.removeProductFromOrder(id, productId), HttpStatus.OK);
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable int id, @RequestBody @Valid Order order){
        orderService.updateOrder(id, order);
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }
    @DeleteMapping("/order/{id}")
    public ResponseEntity<HttpStatus> removeOrder(@PathVariable int id){
        orderService.removeOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
