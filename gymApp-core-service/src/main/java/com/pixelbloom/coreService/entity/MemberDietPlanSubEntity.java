package com.pixelbloom.coreService.entity;

import com.pixelbloom.coreService.enums.SubscriptionStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="member_diet_subscriptions")

public class MemberDietPlanSubEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private long memberid;

    private long dietPlanId;

    private LocalDate startDate;

    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status;

    private  boolean isBundledWithMembership;

    private Long membershipId;

    private BigDecimal paidAmount;

    private String paymentTransactionId;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private int durationMonths;

    private String paymentMethod;

    private String orderNumber;

    private String paymentUrl;



}

