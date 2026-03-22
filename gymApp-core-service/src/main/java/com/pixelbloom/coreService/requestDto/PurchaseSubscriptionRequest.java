package com.pixelbloom.coreService.requestDto;

import lombok.Data;

@Data
public class PurchaseSubscriptionRequest {
    private long memberId;
    private long dietPlanId;
    private int durationMonths;
    private String paymentMethod;
    private  Boolean isBundledWithMembership;
    private Long membershipId;
}
