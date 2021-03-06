package com.example.Blood_Test.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Blood_Test.RazorPay.Order;
import com.example.Blood_Test.RazorPay.Signature;
import com.example.Blood_Test.repository.OrderRepository;

@Service
public class OrderService {
	 
	@Autowired
    private OrderRepository orderRepository;
 
    @Transactional
    public Order saveOrder(final String razorpayOrderId, final Long userId) {
        Order order = new Order();
        order.setRazorpayOrderId(razorpayOrderId);
        order.setUserId(userId);
        return orderRepository.save(order);
    }
 
    @Transactional
    public String validateAndUpdateOrder(final String razorpayOrderId, final String razorpayPaymentId, final String razorpaySignature, final String secret) {
        String errorMsg = null;
        try {
            Order order = orderRepository.findByRazorpayOrderId(razorpayOrderId);
            // Verify if the razorpay signature matches the generated one to
            // confirm the authenticity of the details returned
            String generatedSignature = Signature.calculateRFC2104HMAC(order.getRazorpayOrderId() + "|" + razorpayPaymentId, secret);
            if (generatedSignature.equals(razorpaySignature)) {
                order.setRazorpayOrderId(razorpayOrderId);
                order.setRazorpayPaymentId(razorpayPaymentId);
                order.setRazorpaySignature(razorpaySignature);
                orderRepository.save(order);
            } else {
                errorMsg = "Payment validation failed: Signature doesn't match";
            }
        } catch (Exception e) {
            System.out.println("Payment validation failed");
 
        }
        return errorMsg;
    }
}