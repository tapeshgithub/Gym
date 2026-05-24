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

import com.demo.Gym.models.Plan;
import com.demo.Gym.services.Plan_service;

@RestController
@RequestMapping("gym/plan/")
public class Plan_controller {

    @Autowired
    Plan_service serv;

    @PostMapping("/save")
    public void save(@RequestBody Plan plan) {
        serv.savePlan(plan);
    }

    @GetMapping("/getallplan")
    public ResponseEntity<List<Plan>> getAllPlans() {
        return ResponseEntity.ok(serv.findAllPlans());
    }

    @GetMapping("/getplan/{id}")
    public ResponseEntity<Optional<Plan>> getPlan(@PathVariable Long id) {
        return ResponseEntity.ok(serv.findById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<Plan> updatePlan(@RequestBody Plan plan) {
        return ResponseEntity.ok(serv.updatePlan(plan));
    }

    @DeleteMapping("/deleteplan/{id}")
    public void delete(@PathVariable Long id) {
        serv.deletePlan(id);
    }
}