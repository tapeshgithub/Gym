package com.demo.Gym.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.Gym.models.Member;

@Repository
public interface Gym_member_repo extends JpaRepository<Member, Long> {
	
}