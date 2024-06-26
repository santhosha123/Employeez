package com.demo.employeez.service;

import com.demo.employeez.entity.Employee;
import com.demo.employeez.entity.UserDetailsDto;
import com.demo.employeez.exception.EmployeeNotFoundException;
import com.demo.employeez.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private EmployeeRepository employeeRepository;
    @Autowired
    public UserDetailsService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmail(username);
        System.out.println(username);
        if(employee == null){
            throw new EmployeeNotFoundException("Employee not found");
        }
        return new UserDetailsDto(employee);
    }
}
