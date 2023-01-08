package com.fmiunibuc.FoodDeliveryApp.services.impl;

import com.fmiunibuc.FoodDeliveryApp.entities.Product;
import com.fmiunibuc.FoodDeliveryApp.entities.Restaurant;
import com.fmiunibuc.FoodDeliveryApp.repositories.ProductRepository;
import com.fmiunibuc.FoodDeliveryApp.repositories.RestaurantRepository;
import com.fmiunibuc.FoodDeliveryApp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Product getProductById(int id){
        return productRepository.findById(id).get();
    }
    @Override
    public List<Product> getProductsByRestaurantId(int restaurantId){
        return productRepository.findAllByRestaurantId(restaurantId);
    }
    @Override
    public Product addProduct(Product product, int restaurantId){
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
        product.setRestaurant(restaurant);
        return productRepository.save(product);
    };

    @Override
    public void updateProduct(int id, Product product){
        Product productUpdated = productRepository.findById(id).get();
        productUpdated.setName(product.getName());
        productUpdated.setDescription(product.getDescription());
        productUpdated.setPrice(product.getPrice());
        productUpdated.setCategory(product.getCategory());
        productRepository.save(productUpdated);
    }

    @Override
    public void removeProduct(int id){
        productRepository.deleteById(id);
    };
}
