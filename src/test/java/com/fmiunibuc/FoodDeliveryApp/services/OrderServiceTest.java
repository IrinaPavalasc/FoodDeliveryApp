package com.fmiunibuc.FoodDeliveryApp.services;

import com.fmiunibuc.FoodDeliveryApp.entities.*;
import com.fmiunibuc.FoodDeliveryApp.repositories.*;
import com.fmiunibuc.FoodDeliveryApp.services.impl.OrderServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private DriverRepository driverRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    public void getOrderByIdTest() {
        Order order = new Order(Timestamp.valueOf("2023-01-03 00:00:00"), 50, 20, "In Progress");
        int id = order.getId();
        when(orderRepository.findById(id)).thenReturn(Optional.of(order));

        Order result = orderService.getOrderById(id);

        assertEquals(order, result);

    }

    @Test
    public void getOrdersByRestaurantTest() {
        Restaurant restaurant = new Restaurant("BurgerKing", "Strada 1", "Non-Stop");
        int id = restaurant.getId();
        when(orderRepository.findAllByRestaurantId(id)).thenReturn(Arrays.asList(
                new Order(Timestamp.valueOf("2023-01-03 00:00:00"), 50, 20, "In Progress")
        ));

        List<Order> result = orderService.getOrdersByRestaurantId(id);

        assertEquals(50, result.get(0).getTotalprice());
        assertEquals("In Progress", result.get(0).getStatus());
    }

    @Test
    public void getOrdersByUserTest() {
        User user = new User("Jane", "Strada 1", "0740234564", "jane@gmail.com");
        int id = user.getId();
        when(orderRepository.findAllByUserId(id)).thenReturn(Arrays.asList(
                new Order(Timestamp.valueOf("2023-01-03 00:00:00"), 50, 20, "In Progress")
        ));

        List<Order> result = orderService.getOrdersByUserId(id);

        assertEquals(50, result.get(0).getTotalprice());
        assertEquals("In Progress", result.get(0).getStatus());
    }

    @Test
    public void getOrdersByDriverTest() {
        Driver driver = new Driver("James", "0743961256");
        int id = driver.getId();
        when(orderRepository.findAllByDriverId(id)).thenReturn(Arrays.asList(
                new Order(Timestamp.valueOf("2023-01-03 00:00:00"), 50, 20, "In Progress")
        ));

        List<Order> result = orderService.getOrdersByDriverId(id);

        assertEquals(50, result.get(0).getTotalprice());
        assertEquals("In Progress", result.get(0).getStatus());
    }

    @Test
    public void getProductsByOrderIdTest() {
        Product product = new Product("Iaurt", "150g - fructe", 5, "lactate");
        List<Product> products = Arrays.asList(product);
        Order order = new Order(Timestamp.valueOf("2023-01-03 00:00:00"), 50, 20, "In Progress");
        int id = order.getId();
        order.setProducts(products);
        when(orderRepository.findById(id)).thenReturn(Optional.of(order));

        List<Product> result = orderService.getProductsByOrderId(id);

        assertEquals(product, result.get(0));

    }

    @Test
    public void addOrderTest() {
        Restaurant restaurant = new Restaurant("BurgerKing", "Strada 1", "Non-Stop");
        User user = new User("Jane", "Strada 1", "0740234564", "jane@gmail.com");
        Driver driver = new Driver("James", "0743961256");
        int restaurantId = restaurant.getId();
        int driverId = driver.getId();
        int userId = user.getId();

        Order order = new Order(Timestamp.valueOf("2023-01-03 00:00:00"), 50, 20, "In Progress");
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurant));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(driverRepository.findById(driverId)).thenReturn(Optional.of(driver));

        orderService.addOrder(order, restaurantId, userId, driverId);

        verify(orderRepository, times(1)).save(order);

    }

//    @Test
//    public void addProductToOrderTest(){
//        Order order = new Order(Timestamp.valueOf("2023-01-03 00:00:00"), 50, 20, "In Progress");
//        Restaurant restaurant = new Restaurant("BurgerKing", "Strada 1", "Non-Stop");
//        Product product = new Product("Iaurt", "150g - fructe", 5, "lactate");
//        int id = order.getId();
//        int productId = product.getId();
//        order.setRestaurant(restaurant);
//        when(orderRepository.findById(id)).thenReturn(Optional.of(order));
//        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
//
//
//        orderService.addProductToOrder(id, productId);
//        int result = order.getTotalprice();
//
//        verify(orderRepository, times(1)).save(order);
//        assertEquals(55, result);
//    }

    @Test
    public void removeProductFromOrderTest(){
        Order order = new Order(Timestamp.valueOf("2023-01-03 00:00:00"), 50, 20, "In Progress");
        Product product = new Product("Iaurt", "150g - fructe", 5, "lactate");
        int id = order.getId();
        int productId = product.getId();
        List<Product> products = new ArrayList();
        products.add(product);
        order.setProducts(products);
        when(orderRepository.findById(id)).thenReturn(Optional.of(order));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));


        orderService.removeProductFromOrder(id, productId);
        int result = order.getTotalprice();

        verify(orderRepository, times(1)).save(order);
        assertEquals(45, result);
    }
    @Test
    public void updateOrderTest() {
        Order order = new Order(Timestamp.valueOf("2023-01-03 00:00:00"), 50, 20, "In Progress");
        Order orderUpdated = new Order(Timestamp.valueOf("2022-12-22 00:00:00"), 100, 60, "Delivered");
        int id = orderUpdated.getId();

        when(orderRepository.findById(id)).thenReturn(Optional.of(order));

        orderService.updateOrder(id, order);
        verify(orderRepository, times(1)).save(order);

    }

    @Test
    public void removeOrderTest() {
        Order order = new Order(Timestamp.valueOf("2023-01-03 00:00:00"), 50, 20, "In Progress");
        int id = order.getId();
        orderService.removeOrder(id);
        verify(orderRepository, times(1)).deleteById(id);

    }

}