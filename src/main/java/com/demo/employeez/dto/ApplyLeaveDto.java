package com.demo.employeez.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyLeaveDto {
    int employeeId;
    Long leaveInfoId;
    int type;
    LocalDate applyOn;
    String note;
}
