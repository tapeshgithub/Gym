package com.demo.Gym.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.Gym.DTO.MembershipDTO;
import com.demo.Gym.models.Membership;
import com.demo.Gym.services.Membership_service;

@RestController
@RequestMapping("gym/membership/")
public class Membership_controller {

    @Autowired
    Membership_service serv;

    // Helper: convert Membership entity to MembershipDTO
    private MembershipDTO toDTO(Membership m) {
        if (m == null) return null;
        return new MembershipDTO(
            m.getMembershipId(),
            m.getMember() != null ? m.getMember().getMemberId() : null,
            m.getMember() != null ? m.getMember().getName() : null,
            m.getMember() != null ? m.getMember().getEmail() : null,
            m.getMember() != null ? m.getMember().getPhone() : null,
            m.getPlan()   != null ? m.getPlan().getPlanId() : null,
            m.getPlan()   != null ? m.getPlan().getPlanName() : null,
            m.getPlan()   != null ? m.getPlan().getDurationMonths() : null,
            m.getPlan()   != null && m.getPlan().getPrice() != null
                          ? m.getPlan().getPrice().doubleValue() : null,
            m.getStartDate(),
            m.getEndDate(),
            m.getPaymentStatus()
        );
    }

    @PostMapping("/save")
    public void save(@RequestBody Membership membership) {
        serv.saveMembership(membership);
    }

    @GetMapping("/getallmembership")
    public ResponseEntity<List<MembershipDTO>> getAllMemberships() {
        List<MembershipDTO> dtos = serv.findAllMemberships()
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/getmembership/{id}")
    public ResponseEntity<MembershipDTO> getMembership(@PathVariable Long id) {
        Optional<Membership> opt = serv.findById(id);
        return opt.map(m -> ResponseEntity.ok(toDTO(m)))
                  .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update")
    public ResponseEntity<MembershipDTO> updateMembership(@RequestBody Membership membership) {
        Membership updated = serv.updateMembership(membership);
        return ResponseEntity.ok(toDTO(updated));
    }

    @DeleteMapping("/deletemembership/{id}")
    public void delete(@PathVariable Long id) {
        serv.deleteMembership(id);
    }
}
