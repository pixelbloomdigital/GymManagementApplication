package com.pixelbloom.coreService.service;


import com.pixelbloom.coreService.requestDto.PurchaseSubscriptionRequest;
import com.pixelbloom.coreService.requestDto.PurchaseSubscriptionResponse;

public interface MemberDietPlanSubService {
    PurchaseSubscriptionResponse purchaseSubscription(PurchaseSubscriptionRequest request);
    void handlePaymentCallback(String orderNumber, boolean paymentSuccess);
}
