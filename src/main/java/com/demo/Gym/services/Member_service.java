package com.demo.Gym.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Gym.models.Member;
import com.demo.Gym.repositories.Gym_member_repo;

@Service
public class Member_service {

    @Autowired
    Gym_member_repo repo;

    // Save a member
    public void saveMember(Member member) {
        repo.save(member);
    }

    // Find member by id
    public Optional<Member> findById(Long id) {
        Member member = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found!!!"));
        return Optional.of(member);
    }

    // Find all members
    public List<Member> findAllMembers() {
        return repo.findAll();
    }

    // Delete member
    public void deleteMember(Long id) {
        repo.deleteById(id);
    }

    // Update member
    public Member updateMember(Member member) {
        Member old_data = repo.getById(member.getMemberId());

        old_data.setName(member.getName());
        old_data.setPhone(member.getPhone());
        old_data.setEmail(member.getEmail());
        old_data.setJoinDate(member.getJoinDate());

        repo.save(old_data);
        return old_data;
    }
}