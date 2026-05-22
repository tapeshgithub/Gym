package com.demo.Gym.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Gym.models.Membership;
import com.demo.Gym.repositories.Gym_membership_repo;

@Service
public class Membership_service {

    @Autowired
    Gym_membership_repo repo;

    // Save a membership
    public void saveMembership(Membership membership) {
        repo.save(membership);
    }

    // Find membership by id
    public Optional<Membership> findById(Long id) {
        Membership membership = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Membership not found!!!"));
        return Optional.of(membership);
    }

    // Find all memberships
    public List<Membership> findAllMemberships() {
        return repo.findAll();
    }

    // Delete membership
    public void deleteMembership(Long id) {
        repo.deleteById(id);
    }

    // Update membership
    public Membership updateMembership(Membership membership) {
        Membership old_data = repo.getById(membership.getMembershipId());

        old_data.setMember(membership.getMember());
        old_data.setPlan(membership.getPlan());
        old_data.setStartDate(membership.getStartDate());
        old_data.setEndDate(membership.getEndDate());
        old_data.setPaymentStatus(membership.getPaymentStatus());

        repo.save(old_data);
        return old_data;
    }
}