package com.fmiunibuc.FoodDeliveryApp.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name cannot be missing.")
    @Size(min = 3, max = 30)
    private String name;

    @Column(name = "description")
    @Size(min = 5, max = 200)
    private String description;

    @Column(name = "price", nullable = false)
    @NotNull(message = "Price cannot be missing.")
    @Min(0)
    private int price;

    @Column(name = "category")
    @Size(min = 5, max = 50)
    private String category;

    @ManyToOne(optional = false)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id", unique = true)
    private Restaurant restaurant;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
