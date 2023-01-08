package com.fmiunibuc.FoodDeliveryApp.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name cannot be missing.")
    @Size(min = 3, max = 50)
    private String name;

    @Column(name = "address", nullable = false)
    @NotBlank(message = "Address cannot be missing.")
    @Size(min = 5, max = 150)
    private String address;

    @Column(name = "schedule")
    @Size(max = 100)
    private String schedule;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private List<Product> products;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private List<Driver> drivers;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private List<Order> orders;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }
}
