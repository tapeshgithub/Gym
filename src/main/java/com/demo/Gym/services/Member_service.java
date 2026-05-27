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

    public void saveMember(Member member) {
        repo.save(member);
    }

    public Optional<Member> findById(Long id) {
        Member member = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found!!!"));
        return Optional.of(member);
    }

    public List<Member> findAllMembers() {
        return repo.findAll();
    }

    public void deleteMember(Long id) {
        repo.deleteById(id);
    }

    public Member updateMember(Member member) {
        // Use findById instead of deprecated getById
        Member old_data = repo.findById(member.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + member.getMemberId()));

        old_data.setName(member.getName());
        old_data.setPhone(member.getPhone());
        old_data.setEmail(member.getEmail());
        old_data.setJoinDate(member.getJoinDate());

        return repo.save(old_data);
    }
}
