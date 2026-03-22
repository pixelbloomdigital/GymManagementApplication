package com.pixelbloom.coreService.serviceImpl;
import com.pixelbloom.coreService.entity.MemberDietPlanSubEntity;
import com.pixelbloom.coreService.enums.SubscriptionStatus;
import com.pixelbloom.coreService.repository.DietPlansRepository;
import com.pixelbloom.coreService.repository.MemberDietPlanRepository;
import com.pixelbloom.coreService.requestDto.PurchaseSubscriptionRequest;
import com.pixelbloom.coreService.requestDto.PurchaseSubscriptionResponse;
import com.pixelbloom.coreService.service.MemberDietPlanSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class MemberDietPlanSubServiceImpl implements MemberDietPlanSubService {

    @Autowired private MemberDietPlanRepository repo;
    @Autowired private DietPlansRepository dietPlansRepository;


    @Override
    public PurchaseSubscriptionResponse purchaseSubscription(PurchaseSubscriptionRequest request) {
        // 1. Fetch diet plan to get price
        var dietPlan = dietPlansRepository.findById(request.getDietPlanId())
                .orElseThrow(() -> new RuntimeException("Diet plan not found: " + request.getDietPlanId()));

        // 2. Calculate amount
        BigDecimal amount = dietPlan.getPlan_price().multiply(BigDecimal.valueOf(request.getDurationMonths()));

        // 3. Generate order number
        String orderNumber = "ORD-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "-" + System.currentTimeMillis();

        // 4. Create subscription with PENDING_PAYMENT status
        MemberDietPlanSubEntity subscription = new MemberDietPlanSubEntity();
        subscription.setMemberid(request.getMemberId());
        subscription.setDietPlanId(request.getDietPlanId());
        subscription.setDurationMonths(request.getDurationMonths());
        subscription.setPaymentMethod(request.getPaymentMethod());
        subscription.setStatus(SubscriptionStatus.PENDING_PAYMENT);
        subscription.setPaidAmount(amount);
        subscription.setOrderNumber(orderNumber);
        subscription.setStartDate(LocalDate.now());
        subscription.setEndDate(LocalDate.now().plusMonths(request.getDurationMonths()));
        subscription.setPaymentUrl("https://payment-gateway.com/pay/" + orderNumber);
        subscription.setBundledWithMembership(request.getIsBundledWithMembership());
        subscription.setMembershipId(request.getMembershipId());

        MemberDietPlanSubEntity saved = repo.save(subscription);

        // 5. Build response
        PurchaseSubscriptionResponse response = new PurchaseSubscriptionResponse();
        response.setSubscriptionId(saved.getId());
        response.setStatus(saved.getStatus().name());
        response.setOrderNumber(orderNumber);
        response.setAmount(amount);
        response.setPaymentUrl(saved.getPaymentUrl());

        return response;
    }

    @Override
    public void handlePaymentCallback(String orderNumber, boolean paymentSuccess) {
        MemberDietPlanSubEntity subscription = repo.findByOrderNumber(orderNumber)
                .orElseThrow(() -> new RuntimeException("Subscription not found for order: " + orderNumber));

        subscription.setStatus(paymentSuccess ? SubscriptionStatus.ACTIVE : SubscriptionStatus.CANCELLED);
        repo.save(subscription);
    }
}
