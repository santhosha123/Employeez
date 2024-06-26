package com.demo.employeez.exception.handler;

import com.demo.employeez.exception.EmployeeNotFoundException;
import com.demo.employeez.exception.LeaveNotFoundException;
import com.demo.employeez.exception.response.ErrorResponse;
import com.demo.employeez.exception.DepartmentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorResponse> employeeExceptionHandler(EmployeeNotFoundException exp){
        ErrorResponse employeeErrorResponse = new ErrorResponse();
        employeeErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        employeeErrorResponse.setMessage(exp.getMessage());
        employeeErrorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(employeeErrorResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ErrorResponse> departmentExceptionHandler(EmployeeNotFoundException exp){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(exp.getMessage());
        errorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(LeaveNotFoundException.class)
    public ResponseEntity<ErrorResponse> leaveExceptionHandler(EmployeeNotFoundException exp){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(exp.getMessage());
        errorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
//    @ExceptionHandler
//    public ResponseEntity<ErrorResponse> exceptionHandler(Exception exp){
//        ErrorResponse employeeErrorResponse = new ErrorResponse();
//        employeeErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
//        employeeErrorResponse.setMessage("Employee not found");
//        employeeErrorResponse.setTimestamp(System.currentTimeMillis());
//        return new ResponseEntity<>(employeeErrorResponse,HttpStatus.NOT_FOUND);
//    }
}