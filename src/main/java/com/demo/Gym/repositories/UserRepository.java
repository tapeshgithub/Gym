package com.demo.Gym.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.Gym.models.AppUsers;

@Repository
public interface UserRepository extends JpaRepository<AppUsers, Long> {

    Optional<AppUsers> findByEmail(String email);
}