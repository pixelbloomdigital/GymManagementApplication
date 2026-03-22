package com.pixelbloom.coreService.requestDto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PurchaseSubscriptionResponse {
    private long subscriptionId;
    private String status;
    private String orderNumber;
    private BigDecimal amount;
    private String paymentUrl;

}
