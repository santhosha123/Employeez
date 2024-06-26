package com.demo.employeez.mapper;

import com.demo.employeez.dto.AppliedLeaveDto;
import com.demo.employeez.entity.Leave;

public class LeaveMapper {
    public static AppliedLeaveDto mapToAppliedLeaveDto(Leave leave){
        AppliedLeaveDto leaveDto = AppliedLeaveDto.builder()
                .employeeDto(EmployeeMapper.mapToEmployeeDto(leave.getEmployee()))
                .applyOn(leave.getApplyOn())
                .note(leave.getNote())
                .type(leave.getType())
                .isApproved(leave.getIsApproved())
                .id(leave.getId())
                .build();
        return leaveDto;

    }
}
