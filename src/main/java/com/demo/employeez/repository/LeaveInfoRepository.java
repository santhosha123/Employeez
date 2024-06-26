package com.demo.employeez.repository;

import com.demo.employeez.entity.LeaveInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveInfoRepository extends JpaRepository<LeaveInfo,Long> {
}
