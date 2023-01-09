package com.fmiunibuc.FoodDeliveryApp.services;

import com.fmiunibuc.FoodDeliveryApp.entities.Product;
import com.fmiunibuc.FoodDeliveryApp.entities.Restaurant;
import com.fmiunibuc.FoodDeliveryApp.repositories.ProductRepository;
import com.fmiunibuc.FoodDeliveryApp.repositories.RestaurantRepository;
import com.fmiunibuc.FoodDeliveryApp.services.impl.ProductServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void getProductByIdTest() {
        Product product = new Product("Iaurt", "150g - fructe", 5, "lactate");
        int id = product.getId();
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        Product result = productService.getProductById(id);

        assertEquals(product, result);

    }

    @Test
    public void getProductsByRestaurantTest() {
        Restaurant restaurant = new Restaurant("BurgerKing", "Strada 1", "Non-Stop");
        int id = restaurant.getId();
        when(productRepository.findAllByRestaurantId(id)).thenReturn(Arrays.asList(
                new Product("Iaurt", "150g - fructe", 5, "lactate")
        ));

        List<Product> result = productService.getProductsByRestaurantId(id);

        assertEquals("Iaurt", result.get(0).getName());
    }

    @Test
    public void addProductTest() {
        Restaurant restaurant = new Restaurant("BurgerKing", "Strada 1", "Non-Stop");
        int id = restaurant.getId();
        Product product = new Product("Iaurt", "150g - fructe", 5, "lactate");
        when(restaurantRepository.findById(id)).thenReturn(Optional.of(restaurant));

        productService.addProduct(product, id);

        verify(productRepository, times(1)).save(product);

    }

    @Test
    public void updateProductTest() {
        Product product = new Product("Iaurt", "150g - fructe", 5, "lactate");
        Product productUpdated = new Product("Lapte", "1Litru", 10, "lactate");
        int id = productUpdated.getId();
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        productService.updateProduct(id, product);

        verify(productRepository, times(1)).save(product);

    }

    @Test
    public void removeProductTest() {
        Product product = new Product("Iaurt", "150g - fructe", 5, "lactate");
        int id = product.getId();

        productService.removeProduct(id);

        verify(productRepository, times(1)).deleteById(id);

    }

}