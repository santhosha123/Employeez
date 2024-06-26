package com.demo.employeez.controller;

import com.demo.employeez.dto.DepartmentDto;
import com.demo.employeez.entity.Department;
import com.demo.employeez.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class DepartmentRestController {
    DepartmentService departmentService;
    @Autowired
    public DepartmentRestController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/department")
    public List<DepartmentDto> findAll(){
        List<DepartmentDto> departments = departmentService.findAll();
        return departments;
    }

    @GetMapping("/department/{departmentId}")
    public Department findById(@PathVariable Integer departmentId){
        return departmentService.findById(departmentId);
    }

}
