package com.demo.employeez.service.impl;

import com.demo.employeez.dto.DepartmentDto;
import com.demo.employeez.dto.EmployeeDto;
import com.demo.employeez.entity.Department;
import com.demo.employeez.exception.DepartmentNotFoundException;
import com.demo.employeez.repository.DepartmentRepository;
import com.demo.employeez.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.demo.employeez.mapper.DepartmentMapper.mapToDepartmentDto;
import static com.demo.employeez.mapper.EmployeeMapper.mapToEmployeeDto;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;
    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }


    @Override
    public List<DepartmentDto> findAll() {
        return departmentRepository.findAll().stream()
                .map(department -> mapToDepartmentDto(department))
                .collect(Collectors.toList());
    }

    @Override
    public Department findById(Integer departmentId) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if(department.isPresent()){
            return department.get();
        }
        else {
            throw new DepartmentNotFoundException("Department Not Found");
        }
    }

    @Override
    public List<EmployeeDto> findAllEmployees(Integer departmentId) {
        Optional<Department> department= departmentRepository.findById(departmentId);
        if(department.isPresent()) {
            List<EmployeeDto> employeeDtos = department.get().getEmployees().stream()
                    .map(employee -> mapToEmployeeDto(employee))
                    .collect(Collectors.toList());
            return employeeDtos;
        }else {
            throw new DepartmentNotFoundException("Department Not Found");
        }
    }


}
