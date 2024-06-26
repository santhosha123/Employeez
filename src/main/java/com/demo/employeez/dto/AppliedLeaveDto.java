package com.demo.employeez.dto;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppliedLeaveDto {
    private Long id;
    private LocalDate applyOn;
    private String type;
    private String note;
    private Boolean isApproved;
    private EmployeeDto employeeDto;
}
