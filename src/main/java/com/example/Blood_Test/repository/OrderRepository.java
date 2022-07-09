package com.example.Blood_Test.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.Blood_Test.RazorPay.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
	 
    Order findByRazorpayOrderId(String orderId);
}
