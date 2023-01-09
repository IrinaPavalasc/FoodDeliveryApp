package com.fmiunibuc.FoodDeliveryApp.controllers;

import com.fmiunibuc.FoodDeliveryApp.entities.Product;
import com.fmiunibuc.FoodDeliveryApp.entities.Restaurant;
import com.fmiunibuc.FoodDeliveryApp.services.ProductService;
import com.fmiunibuc.FoodDeliveryApp.services.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Product Controller", description = "Manage Products")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "Get Product", description = "Retrieves all the products belonging to a certain restaurant, based on restaurant ID.")
    @GetMapping("/product/restaurant/{restaurantId}")
    public ResponseEntity<List<Product>> getProductsByRestaurantId(@PathVariable int restaurantId){
        List<Product> productList = (List<Product>) productService.getProductsByRestaurantId(restaurantId);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @Operation(summary = "Add Product", description = "Adds a new product to a restaurant, based on restaurant ID.")
    @PostMapping("/product/add/restaurant/{restaurantId}")
    public ResponseEntity<Product> addProduct(@RequestBody @Valid Product product, @PathVariable int restaurantId){
        return new ResponseEntity<>(productService.addProduct(product, restaurantId), HttpStatus.CREATED);
    }


    @Operation(summary = "Update Product")
    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody @Valid Product product){
        productService.updateProduct(id, product);
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @Operation(summary = "Delete Product")
    @DeleteMapping("/product/{id}")
    public ResponseEntity<HttpStatus> removeProduct(@PathVariable int id){
        productService.removeProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
