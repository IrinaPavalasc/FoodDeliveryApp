package com.fmiunibuc.FoodDeliveryApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "client")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username", nullable = false)
    @NotBlank(message = "Username cannot be missing.")
    @Size(min = 3, max = 50)
    private String username;

    @Column(name = "address", nullable = false)
    @NotBlank(message = "Address cannot be missing.")
    @Size(min = 5, max = 150)
    private String address;

    @Column(name = "phonenumber", nullable = false)
    @NotBlank(message = "Phone number cannot be missing.")
    //@Pattern(regexp = "[0-9]{10}", message = "The phone number must be valid.")
    private String phonenumber;

    @Column(name = "email", nullable = false)
    @NotBlank(message = "Email cannot be missing.")
    @Email(message = "The email must be valid.")
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String username, String address, String phonenumber, String email) {
        this.username = username;
        this.address = address;
        this.phonenumber = phonenumber;
        this.email = email;
    }
}
