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

import com.demo.Gym.models.Member;
import com.demo.Gym.services.Member_service;

@RestController
@RequestMapping("api/member/")
public class Member_controller {

    @Autowired
    Member_service serv;

    @PostMapping("/save")
    public void save(@RequestBody Member member) {
        serv.saveMember(member);
    }

    @GetMapping("/getallmember")
    public ResponseEntity<List<Member>> getAllMembers() {
        return ResponseEntity.ok(serv.findAllMembers());
    }

    @GetMapping("/getmember/{id}")
    public ResponseEntity<Optional<Member>> getMember(@PathVariable Long id) {
        return ResponseEntity.ok(serv.findById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<Member> updateMember(@RequestBody Member member) {
        return ResponseEntity.ok(serv.updateMember(member));
    }

    @DeleteMapping("/deletemember/{id}")
    public void delete(@PathVariable Long id) {
        serv.deleteMember(id);
    }
}