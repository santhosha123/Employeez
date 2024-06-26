package com.demo.employeez.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateByEmployeeDto {
    private int id;
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;
    private LocalDate joinedOn;
    private String designation;
    private String address;
    private Long mobile;
    private String photoUrl;
}
