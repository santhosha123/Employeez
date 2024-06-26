package com.demo.employeez.dto;

import lombok.*;

import java.time.LocalDate;
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmployeeDto {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private LocalDate dateOfBirth;
    private LocalDate joinedOn;
    private String designation;
    private String address;
    private String password;
    private Long mobile;
    private String photoUrl;
    private int reportTo;
    private int deptId;
    private int roleId;
    private int teamId;
}
