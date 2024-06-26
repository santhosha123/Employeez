package com.demo.employeez.service;

import com.demo.employeez.dto.AuthenticationDto;
import com.demo.employeez.dto.LoginDto;
import com.demo.employeez.entity.Employee;
import com.demo.employeez.entity.UserDetailsDto;
import com.demo.employeez.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    JwtService jwtService;
    AuthenticationManager authenticationManager;
    EmployeeRepository employeeRepository;
    @Autowired
    public LoginService(JwtService jwtService, AuthenticationManager authenticationManager,EmployeeRepository employeeRepository) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.employeeRepository = employeeRepository;
    }

    public AuthenticationDto authenticate(LoginDto loginDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );
        Employee employee =  employeeRepository.findByEmail(loginDto.getEmail());
        return AuthenticationDto.builder()
                .jwt(jwtService.generateToken(new UserDetailsDto(employee)))
                .build();
    }
}
