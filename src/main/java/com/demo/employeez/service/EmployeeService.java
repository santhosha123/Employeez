package com.demo.employeez.service;

import com.demo.employeez.dto.*;
import com.demo.employeez.entity.Department;
import com.demo.employeez.entity.Employee;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> findAll();

    EmployeeDto findById(Integer employeeId);

    Employee save(RegisterDto registerDto);

    EmployeeDto update(UpdateByEmployeeDto updateDto, HttpServletRequest request);

    void delete(Integer employeeId);

    Department findDepartment(Integer employeeId);

    EmployeeDto updateEmployee(UpdateEmployeeDto updateDto);

    SearchDto searchEmployees(String query,int offset, String field, String direction);

    UpdateEmployeeDto findByIdForEdit(Integer employeeId);

    List<EmployeeDto> findEmployeesWithPagination(int offset, String field, String direction);

    int findEmployeesCount();

    DashboardDto getDashboardDetails(HttpServletRequest request);

    List<EmployeeDto> findAllManagers();

    ProfileDto getProfileDto(HttpServletRequest request);

    EmployeeDto findEmployeeForUpdate(HttpServletRequest request);
}
