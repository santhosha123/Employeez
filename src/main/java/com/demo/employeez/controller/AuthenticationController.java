package com.demo.employeez.controller;

import com.demo.employeez.service.LoginService;
import com.demo.employeez.service.UserDetailsService;
import com.demo.employeez.dto.AuthenticationDto;
import com.demo.employeez.dto.LoginDto;
import com.demo.employeez.handler.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AuthenticationController {

    LoginService loginService;
    UserDetailsService userDetailsService;
    @Autowired
    public AuthenticationController(LoginService loginService, UserDetailsService userDetailsService) {
        this.loginService = loginService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDto loginDto){
        AuthenticationDto responseDto = loginService.authenticate(loginDto);
        if (responseDto != null)
            return ResponseHandler.generateResponse( responseDto,"Login Successful", HttpStatus.OK);
        else
            return ResponseHandler.generateResponse("Try again",HttpStatus.EXPECTATION_FAILED);    }


}
