package com.demo.employeez.service.impl;

import com.demo.employeez.exception.LeaveNotFoundException;
import com.demo.employeez.repository.EmployeeRepository;
import com.demo.employeez.repository.LeaveInfoRepository;
import com.demo.employeez.repository.LeaveRepository;
import com.demo.employeez.dto.AppliedLeaveDto;
import com.demo.employeez.dto.ApplyLeaveDto;
import com.demo.employeez.entity.Employee;
import com.demo.employeez.entity.Leave;
import com.demo.employeez.entity.LeaveInfo;
import com.demo.employeez.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.demo.employeez.mapper.LeaveMapper.mapToAppliedLeaveDto;

@Service
public class LeaveServiceImpl implements LeaveService {
    LeaveRepository leaveRepository;
    LeaveInfoRepository leaveInfoRepository;
    EmployeeRepository employeeRepository;
    @Autowired
    public LeaveServiceImpl(LeaveRepository leaveRepository,
                            LeaveInfoRepository leaveInfoRepository,
                            EmployeeRepository employeeRepository) {
        this.leaveRepository = leaveRepository;
        this.leaveInfoRepository = leaveInfoRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Leave> findAll() {
        List<Leave> leaves =  leaveRepository.findAll();
        return leaves;
    }

    @Override
    public Leave save(Leave leave) {
        return leaveRepository.save(leave);
    }

    @Override
    public Leave findById(Long leaveId) {
        Optional<Leave> leave = leaveRepository.findById(leaveId);
        if(leave.isPresent()){
            return leave.get();
        }else {
            throw new LeaveNotFoundException("Leave not found");
        }
    }

    @Override
    public LeaveInfo findLeaveInfoById(Long leaveInfoId) {
        LeaveInfo leaveInfo = leaveInfoRepository.findById(leaveInfoId).get();
        return leaveInfo;
    }

    @Override
    public String applyLeave(ApplyLeaveDto applyLeaveDto) {
        Leave leave = new Leave();
        leave.setApplyOn(applyLeaveDto.getApplyOn());
        System.out.println(applyLeaveDto.getApplyOn());
        leave.setNote(applyLeaveDto.getNote());
        leave.setIsApproved(false);
        switch (applyLeaveDto.getType()){
            case 2:leave.setType("earned");
                break;
            case 3:leave.setType("paternity");
                break;
            default:leave.setType("sick");
                break;
        }
        Employee employee = employeeRepository.findById(applyLeaveDto.getEmployeeId()).get();
        leave.setEmployee(employee);
        employee.getLeaves().add(leave);
        employeeRepository.save(employee);
        System.out.println(leave.toString());
        leaveRepository.save(leave);
        return "leave applied successfully";
    }

    @Override
    public List<AppliedLeaveDto> getAppliedLeavesForAdmin() {
        List<AppliedLeaveDto> appliedLeaves = leaveRepository.findAllAppliedLeaves().stream()
                .map(leave -> mapToAppliedLeaveDto(leave))
                .collect(Collectors.toList());
        return appliedLeaves;
    }

    @Override
    public String approveLeave(Long leaveId) {
        Leave leave = leaveRepository.findById(leaveId).get();
        updateEmployeeLeaveInfo(leave.getEmployee(),leave.getType());
        leave.setIsApproved(true);
        leaveRepository.save(leave);
        return "Leave approved";
    }

    private void updateEmployeeLeaveInfo(Employee employee, String type) {
        LeaveInfo leaveInfo = employee.getLeaveInfo();
        switch (type){
            case "earned":leaveInfo.setEarned(leaveInfo.getEarned() - 1);
            break;
            case "paternity":leaveInfo.setPaternity(leaveInfo.getPaternity() - 1);
                break;
            default:leaveInfo.setSick(leaveInfo.getSick() - 1);
        }
        leaveInfoRepository.save(leaveInfo);
    }
}
