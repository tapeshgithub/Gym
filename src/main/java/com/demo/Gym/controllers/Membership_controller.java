package com.demo.Gym.controllers;

import java.util.List;
import java.util.Optional;

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

import com.demo.Gym.models.Membership;
import com.demo.Gym.services.Membership_service;

@RestController
@RequestMapping("api/membership/")
public class Membership_controller {

    @Autowired
    Membership_service serv;

    @PostMapping("/save")
    public void save(@RequestBody Membership membership) {
        serv.saveMembership(membership);
    }

    @GetMapping("/getallmembership")
    public ResponseEntity<List<Membership>> getAllMemberships() {
        return ResponseEntity.ok(serv.findAllMemberships());
    }

    @GetMapping("/getmembership/{id}")
    public ResponseEntity<Optional<Membership>> getMembership(@PathVariable Long id) {
        return ResponseEntity.ok(serv.findById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<Membership> updateMembership(@RequestBody Membership membership) {
        return ResponseEntity.ok(serv.updateMembership(membership));
    }

    @DeleteMapping("/deletemembership/{id}")
    public void delete(@PathVariable Long id) {
        serv.deleteMembership(id);
    }
}