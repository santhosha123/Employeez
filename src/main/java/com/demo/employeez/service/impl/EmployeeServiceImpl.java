package com.demo.employeez.service.impl;

import com.demo.employeez.dto.*;
import com.demo.employeez.exception.EmployeeNotFoundException;
import com.demo.employeez.repository.*;
import com.demo.employeez.service.EmployeeService;
import com.demo.employeez.service.JwtService;
import com.demo.employeez.entity.Department;
import com.demo.employeez.entity.Employee;
import com.demo.employeez.entity.LeaveInfo;
import com.demo.employeez.entity.Role;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.demo.employeez.mapper.EmployeeMapper.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    //add service annotation

    //declare repository
    private EmployeeRepository employeeRepository;
    private RoleRepository roleRepository;
    private HolidayRepository holidayRepository;
    private PasswordEncoder passwordEncoder;
    private DepartmentRepository departmentRepository;
    private LeaveInfoRepository leaveInfoRepository;
    private TeamRepository teamRepository;
    private JwtService jwtService;
    //inject repository using autowired
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               RoleRepository roleRepository,
                               PasswordEncoder passwordEncoder,
                               DepartmentRepository departmentRepository,
                               LeaveInfoRepository leaveInfoRepository,
                               TeamRepository teamRepository,
                               JwtService jwtService,
                               HolidayRepository holidayRepository) {
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.departmentRepository = departmentRepository;
        this.leaveInfoRepository = leaveInfoRepository;
        this.teamRepository = teamRepository;
        this.jwtService = jwtService;
        this.holidayRepository = holidayRepository;
    }

    @Override
    public List<EmployeeDto> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDto> employeeDtos = employees.stream()
                .map(employee -> mapToEmployeeDto(employee))
                .collect(Collectors.toList());
        return employeeDtos;
    }

    @Override
    public EmployeeDto findById(Integer employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        EmployeeDto employeeDto = null;
        if(employee.isPresent())
        {
            employeeDto = mapToEmployeeDto(employee.get());
        }else {
            throw new EmployeeNotFoundException("Employee Not Found");
        }
        return employeeDto;
    }

    @Override
    public Employee save(RegisterDto registerDto) {
        Employee employee = mapToEmployee(registerDto);
        Role role = roleRepository.findById(registerDto.getRoleId()).get();
        employee.setRole(role);
        LeaveInfo leaveInfo = new LeaveInfo(12,12,15);
        System.out.println(leaveInfoRepository.save(leaveInfo));
        employee.setLeaveInfo(leaveInfoRepository.save(leaveInfo));
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setDepartment(departmentRepository.findById(registerDto.getDeptId()).get());
        System.out.println(registerDto.getTeamId());
        System.out.println(teamRepository.findById(registerDto.getTeamId()).get());
        employee.setTeam(teamRepository.findById(registerDto.getTeamId()).get());
        return employeeRepository.save(employee);
    }

    @Override
    public EmployeeDto update(UpdateByEmployeeDto updateDto, HttpServletRequest request) {
        String jwt = request.getHeader("Authorization").substring(7);
        String email = jwtService.extractUsername(jwt);
        Employee employee = employeeRepository.findByEmail(email);
        BeanUtils.copyProperties(updateDto,employee);
        Employee dbRepository = employeeRepository.save(employee);
        return mapToEmployeeDto(dbRepository);
    }

    @Override
    public void delete(Integer employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();
        Long leaveInfoId = employee.getLeaveInfo().getId();
        employeeRepository.deleteById(employeeId);
        leaveInfoRepository.deleteById(leaveInfoId);
    }

    @Override
    public Department findDepartment(Integer employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if(employee.isPresent()){
            Employee dbEmployee = employee.get();
            return dbEmployee.getDepartment();
        }
        else {
            throw new EmployeeNotFoundException("Employee not found");
        }
    }

    @Override
    public EmployeeDto updateEmployee(UpdateEmployeeDto updateDto) {
        Employee employee = employeeRepository.findById(updateDto.getId()).get();
        employee = mapToEmployee(updateDto);
        Role role = roleRepository.findById(updateDto.getRoleId()).get();
        employee.setRole(role);
        employee.setPassword(passwordEncoder.encode(updateDto.getPassword()));
        employee.setDepartment(departmentRepository.findById(updateDto.getDeptId()).get());
        employee.setTeam(teamRepository.findById(updateDto.getTeamId()).get());
        return mapToEmployeeDto(employeeRepository.save(employee));
    }

    @Override
    public SearchDto searchEmployees(String query,int offset, String field, String direction) {
        int itemsPerPage = 3;
        Pageable pageable = PageRequest.of(offset - 1,itemsPerPage, Sort.Direction.valueOf(direction),field);
        int size = employeeRepository.searchEmployees(query).size();
        List<EmployeeDto> employeeDtos =  employeeRepository.searchEmployees(query,pageable).stream().map(employee -> mapToEmployeeDto(employee)).collect(Collectors.toList());
        SearchDto searchDto = new SearchDto(employeeDtos,size);
        return searchDto;
    }

    @Override
    public UpdateEmployeeDto findByIdForEdit(Integer employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if(employee.isPresent()){
            UpdateEmployeeDto updateDto = mapToUpdateEmployeeDto(employee.get());
            return updateDto;
        }else{
            throw new EmployeeNotFoundException("Employee Not Found");
        }
    }

    @Override
    public List<EmployeeDto> findEmployeesWithPagination(int offset, String field, String direction) {
        int itemsPerPage = 3;
        Pageable pageable = PageRequest.of(offset - 1,itemsPerPage, Sort.Direction.valueOf(direction),field);
        Page<Employee> employees = employeeRepository.findAll(pageable);
        List<EmployeeDto> employeeDtos = employees.stream()
                .map(employee -> mapToEmployeeDto(employee))
                .toList();
        System.out.println(employeeDtos);
        return employeeDtos;
    }

    @Override
    public int findEmployeesCount() {
        return (int) employeeRepository.count();
    }

    @Override
    public DashboardDto getDashboardDetails(HttpServletRequest request) {
        String jwt = request.getHeader("Authorization").substring(7);
        String email = jwtService.extractUsername(jwt);
        Employee employee = employeeRepository.findByEmail(email);
        DashboardDto dashboardDto = new DashboardDto();
        dashboardDto.setEmployeeId(employee.getId());
        dashboardDto.setFirstname(employee.getFirstname());
        dashboardDto.setLeaveInfo(employee.getLeaveInfo());
        dashboardDto.setHolidays(holidayRepository.findAll().stream().limit(4).toList());
        dashboardDto.setBirthdayBuddies(employeeRepository.getEmployeeByBirthday(Sort.by("dateOfBirth")).stream()
                .map(employee1 -> mapToEmployeeDto(employee1))
                .map(employee1 -> mapDateOfBirth(employee1))
                .collect(Collectors.toList()));
        dashboardDto.setUpcomingTimeOff(employee.getLeaves().stream()
                .filter(leave -> leave.getApplyOn().isAfter(ChronoLocalDate.from(LocalDateTime.now()))).collect(Collectors.toList()));
        return dashboardDto;
    }

    @Override
    public List<EmployeeDto> findAllManagers() {
        List<Employee> employees = employeeRepository.findByRole(roleRepository.findById(2).get());
        List<EmployeeDto> employeeDtos = employees.stream()
                .map(employee -> mapToEmployeeDto(employee))
                .collect(Collectors.toList());
        return employeeDtos;
    }

    @Override
    public ProfileDto getProfileDto(HttpServletRequest request) {
        String jwt = request.getHeader("Authorization").substring(7);
        String email = jwtService.extractUsername(jwt);
        Employee employee = employeeRepository.findByEmail(email);
        ProfileDto profileDto;
        if(employee.getReportTo() != 0) {
            Employee manager = employeeRepository.findById(employee.getReportTo()).get();
           profileDto = mapToProfileDto(employee, manager);
        }else {
            profileDto = mapToProfileDto(employee);
        }
        return profileDto;
    }

    @Override
    public EmployeeDto findEmployeeForUpdate(HttpServletRequest request) {
        String jwt = request.getHeader("Authorization").substring(7);
        String email = jwtService.extractUsername(jwt);
        Employee employee = employeeRepository.findByEmail(email);
        EmployeeDto employeeDto =  mapToEmployeeDto(employee);
        System.out.println(employeeDto.toString());
        return employeeDto;
    }
}
