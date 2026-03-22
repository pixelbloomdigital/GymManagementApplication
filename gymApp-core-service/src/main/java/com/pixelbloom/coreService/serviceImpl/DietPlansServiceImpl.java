package com.pixelbloom.coreService.serviceImpl;

import com.pixelbloom.coreService.entity.DietPlansEntity;
import com.pixelbloom.coreService.repository.DietPlansRepository;
import com.pixelbloom.coreService.service.DietPlansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class DietPlansServiceImpl implements DietPlansService {

    @Autowired private DietPlansRepository repo;

    @Override public List<DietPlansEntity> getAllPlans() {
        return repo.findAll();
    }

    @Override
    public Optional<DietPlansEntity> getPlanById(int plan_id) {
        return repo.findById((long)plan_id);
    }

    @Override
    public DietPlansEntity createPlan(DietPlansEntity plan) {
        return repo.save(plan);
    }

    @Override
    public DietPlansEntity updatePlan(DietPlansEntity plan, int planId) {
        return repo.findById((long) planId).map(existing -> {
            existing.setPlan_name(plan.getPlan_name());
            existing.setPlan_description(plan.getPlan_description());
            existing.setPlan_price(plan.getPlan_price());
            existing.setPlan_duration(plan.getPlan_duration());
            existing.setPlan_isStandAlone(plan.getPlan_isStandAlone());
            existing.setPlan_isActive(plan.getPlan_isActive());
            return repo.save(existing);
        }).orElseThrow(() -> new RuntimeException("Plan not found with id: " + planId));
    }

    @Override
    public void deletePlan(int planId) {
        if (!repo.existsById((long) planId)) {
            throw new RuntimeException("Plan not found with id: " + planId);
        }
        repo.deleteById((long) planId);
    }


}
