package com.fmiunibuc.FoodDeliveryApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "food_order", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"restaurant_id", "driver_id", "user_id"})
})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "order_date", nullable = false)
    @NotEmpty(message = "The order date cannot be missing.")
    private String orderdate;

    @Column(name = "total_price", nullable = false)
    @NotNull(message = "The total price cannot be missing.")
    @Min(0)
    private int totalprice;

    @Column(name = "predicted_delivery_time", nullable = false)
    @NotNull(message = "The predicted delivery time cannot be missing.")
    @Min(0)
    private int predictedDeliveryTime;

    @Column(name = "status", nullable = false)
    @NotBlank(message = "The status cannot be missing.")
    @Size(min = 3, max = 30)
    private String status;

    @ManyToOne()
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    private Restaurant restaurant;

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    private Driver driver;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    private List<Product> products;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public int getPredictedDeliveryTime() {
        return predictedDeliveryTime;
    }

    public void setPredictedDeliveryTime(int predictedDeliveryTime) {
        this.predictedDeliveryTime = predictedDeliveryTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
