package com.fmiunibuc.FoodDeliveryApp.exception;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(){
        super("No order found!");
    }
}
