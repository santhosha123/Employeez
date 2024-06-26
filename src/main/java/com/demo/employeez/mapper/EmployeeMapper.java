package com.demo.employeez.mapper;

import com.demo.employeez.dto.EmployeeDto;
import com.demo.employeez.dto.ProfileDto;
import com.demo.employeez.dto.RegisterDto;
import com.demo.employeez.dto.UpdateEmployeeDto;
import com.demo.employeez.entity.Employee;

import java.time.LocalDate;

public class EmployeeMapper {
    public static EmployeeDto mapToEmployeeDto(Employee employee){
        EmployeeDto employeeDto = EmployeeDto.builder()
                .id(employee.getId())
                .firstname(employee.getFirstname())
                .lastname(employee.getLastname())
                .designation(employee.getDesignation())
                .address(employee.getAddress())
                .email(employee.getEmail())
                .dateOfBirth(employee.getDateOfBirth())
                .photoUrl(employee.getPhotoUrl())
                .mobile(employee.getMobile())
                .joinedOn(employee.getJoinedOn())
                .reportTo(employee.getReportTo())
                .department(employee.getDepartment())
                .build();
        return employeeDto;
    }

    public static Employee mapToEmployee(RegisterDto registerDto){
        Employee employee = Employee.builder()
                .firstname(registerDto.getFirstname())
                .lastname(registerDto.getLastname())
                .email(registerDto.getEmail())
                .dateOfBirth(registerDto.getDateOfBirth())
                .joinedOn(registerDto.getJoinedOn())
                .designation(registerDto.getDesignation())
                .address(registerDto.getAddress())
                .password(registerDto.getPassword())
                .mobile(registerDto.getMobile())
                .photoUrl(registerDto.getPhotoUrl())
                .reportTo(registerDto.getReportTo())
                .build();
        return employee;
    }
    public static Employee mapToEmployee(UpdateEmployeeDto updateDto){
        Employee employee = Employee.builder()
                .id(updateDto.getId())
                .firstname(updateDto.getFirstname())
                .lastname(updateDto.getLastname())
                .email(updateDto.getEmail())
                .dateOfBirth(updateDto.getDateOfBirth())
                .joinedOn(updateDto.getJoinedOn())
                .designation(updateDto.getDesignation())
                .address(updateDto.getAddress())
                .password(updateDto.getPassword())
                .mobile(updateDto.getMobile())
                .photoUrl(updateDto.getPhotoUrl())
                .reportTo(updateDto.getReportTo())
                .build();
        return employee;
    }
    public static UpdateEmployeeDto mapToUpdateEmployeeDto(Employee employee){
        UpdateEmployeeDto updateDto = UpdateEmployeeDto.builder()
                .id(employee.getId())
                .firstname(employee.getFirstname())
                .lastname(employee.getLastname())
                .email(employee.getEmail())
                .dateOfBirth(employee.getDateOfBirth())
                .joinedOn(employee.getJoinedOn())
                .designation(employee.getDesignation())
                .address(employee.getAddress())
                .password(employee.getPassword())
                .mobile(employee.getMobile())
                .photoUrl(employee.getPhotoUrl())
                .reportTo(employee.getReportTo())
                .roleId(employee.getRole().getId())
                .deptId(employee.getDepartment().getId())
                .teamId(employee.getTeam().getId())
                .build();
        return updateDto;
    }
    public static EmployeeDto mapDateOfBirth(EmployeeDto employeeDto){
        String dateOfBirth = employeeDto.getDateOfBirth().toString();
        int currentYear = LocalDate.now().getYear();
        int birthYear = Integer.parseInt(dateOfBirth.substring(0, 4));
        int month = Integer.parseInt(dateOfBirth.substring(5, 7));
        int day = Integer.parseInt(dateOfBirth.substring(8, 10));
        employeeDto.setDateOfBirth(LocalDate.of(currentYear, month, day));
        return employeeDto;
    }

    public static ProfileDto mapToProfileDto(Employee employee, Employee manager){
        ProfileDto profileDto = ProfileDto.builder()
                .id(employee.getId())
                .firstname(employee.getFirstname())
                .lastname(employee.getLastname())
                .email(employee.getEmail())
                .department(employee.getDepartment().getName())
                .designation(employee.getDesignation())
                .photoUrl(employee.getPhotoUrl())
                .mobile(employee.getMobile())
                .managerId(manager.getId())
                .managerDesignation(manager.getDesignation())
                .managerName(manager.getFirstname())
                .build();
        return profileDto;
    }
    public static ProfileDto mapToProfileDto(Employee employee){
        ProfileDto profileDto = ProfileDto.builder()
                .id(employee.getId())
                .firstname(employee.getFirstname())
                .lastname(employee.getLastname())
                .email(employee.getEmail())
                .department(employee.getDepartment().getName())
                .designation(employee.getDesignation())
                .photoUrl(employee.getPhotoUrl())
                .mobile(employee.getMobile())
                .build();
        return profileDto;
    }
}
