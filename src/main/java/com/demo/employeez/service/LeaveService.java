package com.demo.employeez.service;

import com.demo.employeez.dto.AppliedLeaveDto;
import com.demo.employeez.dto.ApplyLeaveDto;
import com.demo.employeez.entity.Leave;
import com.demo.employeez.entity.LeaveInfo;

import java.util.List;

public interface LeaveService {
    List<Leave> findAll();

    Leave save(Leave leave);

    Leave findById(Long leaveId);

    LeaveInfo findLeaveInfoById(Long leaveInfoId);

    String applyLeave(ApplyLeaveDto applyLeaveDto);

    List<AppliedLeaveDto> getAppliedLeavesForAdmin();
    String approveLeave(Long leaveId);
}
