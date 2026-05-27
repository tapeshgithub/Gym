package com.demo.Gym.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Gym.models.Plan;
import com.demo.Gym.repositories.Gym_plan_repo;

@Service
public class Plan_service {

    @Autowired
    Gym_plan_repo repo;

    public void savePlan(Plan plan) {
        repo.save(plan);
    }

    public Optional<Plan> findById(Long id) {
        Plan plan = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Plan not found!!!"));
        return Optional.of(plan);
    }

    public List<Plan> findAllPlans() {
        return repo.findAll();
    }

    public void deletePlan(Long id) {
        repo.deleteById(id);
    }

    public Plan updatePlan(Plan plan) {
        // Use findById instead of deprecated getById
        Plan old_data = repo.findById(plan.getPlanId())
                .orElseThrow(() -> new RuntimeException("Plan not found with id: " + plan.getPlanId()));

        old_data.setPlanName(plan.getPlanName());
        old_data.setDurationMonths(plan.getDurationMonths());
        old_data.setPrice(plan.getPrice());

        return repo.save(old_data);
    }
}
