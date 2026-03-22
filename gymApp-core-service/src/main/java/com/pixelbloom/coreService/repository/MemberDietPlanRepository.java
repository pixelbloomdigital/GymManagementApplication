package com.pixelbloom.coreService.repository;

import com.pixelbloom.coreService.entity.MemberDietPlanSubEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository


public interface MemberDietPlanRepository extends JpaRepository<MemberDietPlanSubEntity , Long> {

    Optional<MemberDietPlanSubEntity> findByOrderNumber(String orderNumber);
}

