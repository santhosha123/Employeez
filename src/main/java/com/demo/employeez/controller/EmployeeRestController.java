package com.demo.employeez.controller;

import com.demo.employeez.dto.*;
import com.demo.employeez.entity.Department;
import com.demo.employeez.entity.Employee;
import com.demo.employeez.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class EmployeeRestController {

    //declare employeeService
    private EmployeeService employeeService;
    //inject employeeService
    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    //list all employees
    @GetMapping("/employees/all")
    public List<EmployeeDto> findAllEmployees(){
        List<EmployeeDto> employees = employeeService.findAll();
        return employees;
    }

    //find employee by id
    @GetMapping("/employees/{employeeId}")
    public EmployeeDto findEmployeeById(@PathVariable Integer employeeId){
        EmployeeDto employee = employeeService.findById(employeeId);
        return employee;
    }
    //add an Employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody RegisterDto registerDto){
        System.out.println("hfhm");
        return employeeService.save(registerDto);
    }
    //update an Employee
    @PutMapping("/employees")
    public EmployeeDto updateEmployee(@RequestBody UpdateByEmployeeDto updateDto, HttpServletRequest request){
        return employeeService.update(updateDto,request);
    }

    @PutMapping("/employees/edit")
    public EmployeeDto editEmployee(@RequestBody UpdateEmployeeDto updateDto ){
        return employeeService.updateEmployee(updateDto);
    }
    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable Integer employeeId){
        employeeService.delete(employeeId);
    }

    @GetMapping("/employees/dept/{employeeId}")
    public Department  findDepartment(@PathVariable Integer employeeId){
        return employeeService.findDepartment(employeeId);
    }
    @GetMapping("/employees/{searchQuery}/{offset}/{field}/{direction}")
    public SearchDto searchEmployees(@PathVariable String searchQuery,
                                             @PathVariable int offset,
                                             @PathVariable String field,
                                             @PathVariable String direction
                                             ){
        SearchDto searchDto = employeeService.searchEmployees(searchQuery,offset,field,direction);
        return searchDto;
    }
    //for update
    @GetMapping("/employees/edit/{employeeId}")
    public UpdateEmployeeDto findEmployeeByIdForEdit(@PathVariable Integer employeeId){
        UpdateEmployeeDto updateEmployeeDto = employeeService.findByIdForEdit(employeeId);
        return updateEmployeeDto;
    }

    @GetMapping("/employees/{offset}/{field}/{direction}")
    public List<EmployeeDto> findEmployeesWithPagination(@PathVariable int offset,
                                                         @PathVariable String field,
                                                         @PathVariable String direction){
        List<EmployeeDto> employees = employeeService.findEmployeesWithPagination(offset, field,
        direction);
        return employees;
    }

    @GetMapping("/employees/count")
    public int findEmployeesCount(HttpServletRequest request){
        System.out.println("find Employees count method called");
        int count = employeeService.findEmployeesCount();
        System.out.println(count);
        return count;
    }

    //dashboard details
    @GetMapping("/dashboard")
    public DashboardDto getDashboardDetails(HttpServletRequest request){
        DashboardDto dashboardDto = employeeService.getDashboardDetails(request);
        return dashboardDto;
    }
    //managers
    @GetMapping("/managers")
    public List<EmployeeDto> getManagers(){
        List<EmployeeDto> managers = employeeService.findAllManagers();
        return managers;
    }


    //ProfileDto
    @GetMapping("/profileDto")
    public ProfileDto getProfileDto(HttpServletRequest request){
        ProfileDto profileDto = employeeService.getProfileDto(request);
        return profileDto;
    }

    @GetMapping("/employees/profileDetails")
    public EmployeeDto getProfileDetails(HttpServletRequest request){
        EmployeeDto employeeDto = employeeService.findEmployeeForUpdate(request);
        return employeeDto;
    }
}
