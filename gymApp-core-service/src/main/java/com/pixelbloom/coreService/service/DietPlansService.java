package com.pixelbloom.coreService.service;

import com.pixelbloom.coreService.entity.DietPlansEntity;

import java.util.List;
import java.util.Optional;

public interface DietPlansService {

    List<DietPlansEntity> getAllPlans();

    Optional<DietPlansEntity> getPlanById(int plan_id);

    DietPlansEntity createPlan(DietPlansEntity plan);

    DietPlansEntity updatePlan(DietPlansEntity plan, int planId);

    void deletePlan(int planId);

}
