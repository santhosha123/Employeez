package com.demo.employeez.dto;

import com.demo.employeez.entity.Holiday;
import com.demo.employeez.entity.Leave;
import com.demo.employeez.entity.LeaveInfo;
import lombok.*;

import java.util.List;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDto {
    private String firstname;
    private int employeeId;
    private List<Holiday> holidays;

    private List<EmployeeDto> birthdayBuddies;

    private LeaveInfo leaveInfo;

    private List<Leave> upcomingTimeOff;
}
