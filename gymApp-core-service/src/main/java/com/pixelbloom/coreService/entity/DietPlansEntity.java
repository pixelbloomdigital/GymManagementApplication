package com.pixelbloom.coreService.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="active_plans")

public class DietPlansEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long plan_id;

    private String plan_name;

    @Column(length = 1000)
    private String plan_description;

    private BigDecimal plan_price;

    private int plan_duration;

    private Boolean plan_isStandAlone;

    private Boolean plan_isActive;

    @CreationTimestamp private LocalDateTime plan_createdAt;

    @UpdateTimestamp private LocalDateTime plan_updatedAt;

}
