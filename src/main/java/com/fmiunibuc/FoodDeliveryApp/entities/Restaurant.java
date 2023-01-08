package com.fmiunibuc.FoodDeliveryApp.entities;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name cannot be missing.")
    @Size(min = 5, max = 30)
    private String name;

    @Column(name = "address", nullable = false)
    @NotBlank(message = "Address cannot be missing.")
    @Size(min = 5, max = 150)
    private String address;

    @Column(name = "schedule")
    @Size(max = 100)
    private String schedule;

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
}
