package com.demo.employeez.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {
    int id;
    String firstname;
    String lastname;
    String email;
    String designation;
    String department;
    String photoUrl;
    Long mobile;
    int managerId;
    String managerName;
    String managerDesignation;
}
