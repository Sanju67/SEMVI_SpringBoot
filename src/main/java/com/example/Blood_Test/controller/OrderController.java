package com.example.Blood_Test.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.Blood_Test.RazorPay.OrderRequest;
import com.example.Blood_Test.RazorPay.OrderResponse;
import com.example.Blood_Test.RazorPay.PaymentResponse;
import com.example.Blood_Test.RazorPay.RazorPayClientConfig;
import com.example.Blood_Test.model.CurrentUser;
import com.example.Blood_Test.model.Patient;
import com.example.Blood_Test.service.OrderService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class OrderController {
 
    private RazorpayClient client;
 
    private RazorPayClientConfig razorPayClientConfig;
    
    @Autowired
    private OrderService orderService;
 
    @Autowired
    public OrderController(RazorPayClientConfig razorpayClientConfig) throws RazorpayException {
        this.razorPayClientConfig = razorpayClientConfig;
        this.client = new RazorpayClient(razorpayClientConfig.getKey(), razorpayClientConfig.getSecret());
    }
 
    @RequestMapping(method = RequestMethod.POST, value = "/createOrder")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest,@CurrentUser Patient user) {
    	System.out.println("request system...+ user detail" + user.getFirstName());
    	
        OrderResponse razorPay = null;
        try {
            // The transaction amount is expressed in the currency subunit, such
            // as paise (in case of INR)
            String amountInPaise = convertRupeeToPaise(orderRequest.getAmount());
            // Create an order in RazorPay and get the order id
            Order order = createRazorPayOrder(amountInPaise);
            razorPay = getOrderResponse((String) order.get("id"), amountInPaise);
            // Save order in the database
            orderService.saveOrder(razorPay.getRazorpayOrderId(),(long) user.getId());
        } catch (RazorpayException e) {
           // log.error("Exception while create payment order", e);
            return new ResponseEntity<>(new ApiResponse(false, "Error while create payment order: " + e.getMessage()), HttpStatus.EXPECTATION_FAILED);
        }
        return ResponseEntity.ok(razorPay);
    }
 
    @RequestMapping(method = RequestMethod.PUT, value = "/updateOrder")
    public ResponseEntity<?> updateOrder(@RequestBody PaymentResponse paymentResponse) {
        String errorMsg = orderService.validateAndUpdateOrder(paymentResponse.getRazorpayOrderId(), paymentResponse.getRazorpayPaymentId(), paymentResponse.getRazorpaySignature(),
                razorPayClientConfig.getSecret());
        if (errorMsg != null) {
            return new ResponseEntity<>(new ApiResponse(false, errorMsg), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(new ApiResponse(true, paymentResponse.getRazorpayPaymentId()));
    }
 
    private OrderResponse getOrderResponse(String orderId, String amountInPaise) {
        OrderResponse razorPay = new OrderResponse();
        razorPay.setApplicationFee(amountInPaise);
        razorPay.setRazorpayOrderId(orderId);
        razorPay.setSecretKey(razorPayClientConfig.getKey());
        return razorPay;
    }
 
    private Order createRazorPayOrder(String amount) throws RazorpayException {
        JSONObject options = new JSONObject();
        options.put("amount", amount);
        options.put("currency", "INR");
        options.put("receipt", "txn_123456");
        return client.Orders.create(options);
    }
 
    private String convertRupeeToPaise(String paise) {
        BigDecimal b = new BigDecimal(paise);
        BigDecimal value = b.multiply(new BigDecimal("100"));
        return value.setScale(0, RoundingMode.UP).toString();
    }
}