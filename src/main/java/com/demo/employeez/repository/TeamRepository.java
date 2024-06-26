package com.demo.employeez.repository;

import com.demo.employeez.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface TeamRepository extends JpaRepository<Team, Integer> {
}
