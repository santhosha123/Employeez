package com.demo.employeez.repository;

import com.demo.employeez.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LeaveRepository extends JpaRepository<Leave,Long> {
    @Query("SELECT L FROM Leave L WHERE  L.isApproved = false")
    List<Leave> findAllAppliedLeaves();
}
