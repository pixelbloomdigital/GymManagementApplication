package com.pixelbloom.coreService.controller;

import com.pixelbloom.coreService.entity.DietPlansEntity;
import com.pixelbloom.coreService.service.DietPlansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/diet-plans")

public class DietPlansController {

    @Autowired public DietPlansService service;

    @GetMapping("/plan-details/All-Plans")
 //   @PreAuthorize("hasRole('ADMIN')")
    public List<DietPlansEntity> getAllPlans() {
        return service.getAllPlans();
    }

    @GetMapping("plan-details/{plan_id}")
  //  @PreAuthorize("hasRole('ADMIN')")
    public Optional<DietPlansEntity> getPlanById(@PathVariable int plan_id) {
        return service.getPlanById(plan_id);
    }

    @PostMapping("/plan-details/post-newPlans")
  //  @PreAuthorize("hasRole('ADMIN')")
    public DietPlansEntity createPlan(@RequestBody DietPlansEntity plan) {
        return service.createPlan(plan);
    }

    @PutMapping("/plan-details/update-plan/{plan_id}")
 //   @PreAuthorize("hasRole('ADMIN')")
    public DietPlansEntity updatePlan(@RequestBody DietPlansEntity plan, @PathVariable int plan_id) {
        return service.updatePlan(plan, plan_id);
    }

    @DeleteMapping("/plan-details/delete-plan/{plan_id}")
   // @PreAuthorize("hasRole('ADMIN')")
    public String deletePlan(@PathVariable int plan_id) {
        service.deletePlan(plan_id);
        return "Plan deleted successfully";
    }

}



