package com.fmiunibuc.FoodDeliveryApp.controllers;

import com.fmiunibuc.FoodDeliveryApp.entities.Order;
import com.fmiunibuc.FoodDeliveryApp.entities.Product;
import com.fmiunibuc.FoodDeliveryApp.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Order Controller", description = "Manage Orders")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Operation(summary = "Get Orders By Restaurant", description = "Retrieves all orders belonging to a certain restaurant, based on restaurant ID.")
    @GetMapping("/order/restaurant/{restaurantId}")
    public ResponseEntity<List<Order>> getOrdersByRestaurantId(@PathVariable int restaurantId){
        List<Order> orderList = (List<Order>) orderService.getOrdersByRestaurantId(restaurantId);
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @Operation(summary = "Get Orders By User", description = "Retrieves all orders belonging to a certain user, based on user ID.")
    @GetMapping("/order/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable int userId){
        List<Order> orderList = (List<Order>) orderService.getOrdersByUserId(userId);
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @Operation(summary = "Get Orders By Driver", description = "Retrieves all orders belonging to a certain driver, based on driver ID.")
    @GetMapping("/order/driver/{driverId}")
    public ResponseEntity<List<Order>> getOrdersByDriverId(@PathVariable int driverId){
        List<Order> orderList = (List<Order>) orderService.getOrdersByDriverId(driverId);
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @Operation(summary = "Get Ordered Products", description = "Retrieves all products belonging to a order, based on order ID.")
    @GetMapping("/order/{id}/products")
    public ResponseEntity<List<Product>> getProductsByOrderId(@PathVariable int id){
        List<Product> productList = (List<Product>) orderService.getProductsByOrderId(id);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @Operation(summary = "Add Order", description = "Adds an order. The order is assigned to a certain restaurant, user, driver.")
    @PostMapping("/order/add/restaurant/{restaurantId}/user/{userId}/driver/{driverId}")
    public ResponseEntity<Order> addOrder(@RequestBody @Valid Order order, @PathVariable int restaurantId, @PathVariable int userId, @PathVariable int driverId){
        return new ResponseEntity<>(orderService.addOrder(order, restaurantId, userId, driverId), HttpStatus.CREATED);
    }

    @Operation(summary = "Add Product To Order", description = "Adds a new product to an order, using product ID. The TotalPrice of the order changes is accordance to the price of the products added.")
    @PutMapping("/order/add/{id}/product/{productId}")
    public ResponseEntity<Order> addProductToOrder(@PathVariable int id, @PathVariable int productId){
        return new ResponseEntity<>(orderService.addProductToOrder(id, productId), HttpStatus.OK);
    }

    @Operation(summary = "Remove Product From Order", description = "Removes a product from an existing order, using product ID.  The TotalPrice of the order changes is accordance to the price of the products removed.")
    @PutMapping("/order/remove/{id}/product/{productId}")
    public ResponseEntity<Order> removeProductFromOrder(@PathVariable int id, @PathVariable int productId){
        return new ResponseEntity<>(orderService.removeProductFromOrder(id, productId), HttpStatus.OK);
    }

    @Operation(summary = "Update Order")
    @PutMapping("/order/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable int id, @RequestBody @Valid Order order){
        orderService.updateOrder(id, order);
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }

    @Operation(summary = "Delete Order")
    @DeleteMapping("/order/{id}")
    public ResponseEntity<HttpStatus> removeOrder(@PathVariable int id){
        orderService.removeOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
