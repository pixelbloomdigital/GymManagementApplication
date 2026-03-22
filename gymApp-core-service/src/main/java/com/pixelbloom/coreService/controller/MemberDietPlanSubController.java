package com.pixelbloom.coreService.controller;


import com.pixelbloom.coreService.requestDto.PurchaseSubscriptionRequest;
import com.pixelbloom.coreService.requestDto.PurchaseSubscriptionResponse;
import com.pixelbloom.coreService.service.MemberDietPlanSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/diet-subscriptions")
public class MemberDietPlanSubController {

    @Autowired private MemberDietPlanSubService service;

    @PostMapping("/purchase")
    @ResponseStatus(HttpStatus.CREATED)
    public PurchaseSubscriptionResponse purchase(@RequestBody PurchaseSubscriptionRequest request) {
        return service.purchaseSubscription(request);
    }

    @PostMapping("/payment-callback")
    public String paymentCallback(@RequestParam String orderNumber, @RequestParam boolean success) {
        service.handlePaymentCallback(orderNumber, success);
        return success ? "Subscription activated" : "Subscription cancelled";
    }
}