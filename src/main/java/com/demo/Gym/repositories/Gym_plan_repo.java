package com.demo.Gym.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.Gym.models.Plan;

@Repository
public interface Gym_plan_repo extends JpaRepository<Plan, Long> {
	
}