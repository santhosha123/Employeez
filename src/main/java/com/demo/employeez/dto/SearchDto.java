package com.demo.employeez.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchDto {
    List<EmployeeDto> employees = new ArrayList<>();
    int size;
}
