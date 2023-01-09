package com.fmiunibuc.FoodDeliveryApp.controllers;

import com.fmiunibuc.FoodDeliveryApp.entities.Driver;
import com.fmiunibuc.FoodDeliveryApp.entities.Driver;
import com.fmiunibuc.FoodDeliveryApp.services.DriverService;
import com.fmiunibuc.FoodDeliveryApp.services.DriverService;
import com.fmiunibuc.FoodDeliveryApp.services.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Driver Controller", description = "Manage Drivers")
@RestController
public class DriverController {
    @Autowired
    private DriverService driverService;

    @Operation(summary = "Get Driver By Restaurant", description = "Retrieves all the drivers belonging to a certain restaurant, based on restaurant ID.")
    @GetMapping("/driver/restaurant/{restaurantId}")
    public ResponseEntity<List<Driver>> getDriversByRestaurantId(@PathVariable int restaurantId){
        List<Driver> driverList = (List<Driver>) driverService.getDriversByRestaurantId(restaurantId);
        return new ResponseEntity<>(driverList, HttpStatus.OK);
    }

    @Operation(summary = "Add Driver", description = "Adds a new driver to a restaurant, based on restaurant ID.")
    @PostMapping("/driver/add/restaurant/{restaurantId}")
    public ResponseEntity<Driver> addDriver(@RequestBody @Valid Driver driver, @PathVariable int restaurantId){
        return new ResponseEntity<>(driverService.addDriver(driver, restaurantId), HttpStatus.CREATED);
    }

    @Operation(summary = "Update Driver")
    @PutMapping("/driver/{id}")
    public ResponseEntity<Driver> updateDriver(@PathVariable int id, @RequestBody @Valid Driver driver){
        driverService.updateDriver(id, driver);
        return new ResponseEntity<>(driverService.getDriverById(id), HttpStatus.OK);
    }

    @Operation(summary = "Delete Driver")
    @DeleteMapping("/driver/{id}")
    public ResponseEntity<HttpStatus> removeDriver(@PathVariable int id){
        driverService.removeDriver(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
