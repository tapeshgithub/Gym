package com.demo.Gym.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.Gym.models.Membership;

@Repository
public interface Gym_membership_repo extends JpaRepository<Membership, Long> {
	
}