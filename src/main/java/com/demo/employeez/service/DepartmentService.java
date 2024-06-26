package com.demo.employeez.service;

import com.demo.employeez.dto.DepartmentDto;
import com.demo.employeez.dto.EmployeeDto;
import com.demo.employeez.entity.Department;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDto> findAll();

    Department findById(Integer departmentId);

    List<EmployeeDto> findAllEmployees(Integer departmenId);
}
