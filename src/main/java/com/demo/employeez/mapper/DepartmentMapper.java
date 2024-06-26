package com.demo.employeez.mapper;

import com.demo.employeez.dto.DepartmentDto;
import com.demo.employeez.entity.Department;

public class DepartmentMapper {
    public static DepartmentDto mapToDepartmentDto(Department department){
        DepartmentDto departmentDto = DepartmentDto.builder()
                .id(department.getId())
                .name(department.getName())
                .employeesCount(department.getEmployees().size())
                .build();
        return departmentDto;
    }
}
