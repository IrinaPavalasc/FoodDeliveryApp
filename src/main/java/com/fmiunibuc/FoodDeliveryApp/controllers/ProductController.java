package com.fmiunibuc.FoodDeliveryApp.controllers;

import com.fmiunibuc.FoodDeliveryApp.entities.Product;
import com.fmiunibuc.FoodDeliveryApp.entities.Restaurant;
import com.fmiunibuc.FoodDeliveryApp.services.ProductService;
import com.fmiunibuc.FoodDeliveryApp.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;
    @GetMapping("/product/{restaurantId}")
    public ResponseEntity<List<Product>> getProductsByRestaurantId(@PathVariable int restaurantId){
        List<Product> productList = (List<Product>) productService.getProductsByRestaurantId(restaurantId);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }
    @PostMapping("/product/add/{restaurantId}")
    public ResponseEntity<Product> addProduct(@RequestBody @Valid Product product, @PathVariable int restaurantId){
        return new ResponseEntity<>(productService.addProduct(product, restaurantId), HttpStatus.CREATED);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody @Valid Product product){
        productService.updateProduct(id, product);
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }
    @DeleteMapping("/product/{id}")
    public ResponseEntity<HttpStatus> removeProduct(@PathVariable int id){
        productService.removeProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
