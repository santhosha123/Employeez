package com.demo.employeez.controller;

import com.demo.employeez.dto.AppliedLeaveDto;
import com.demo.employeez.dto.ApplyLeaveDto;
import com.demo.employeez.entity.Leave;
import com.demo.employeez.entity.LeaveInfo;
import com.demo.employeez.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class LeaveRestController {
    LeaveService leaveService;
    @Autowired
    public LeaveRestController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }
    @GetMapping("/leave")
    public List<Leave> findAll(){
       return leaveService.findAll();
    }

    @PostMapping("/leave")
    public Leave applyLeave(@RequestBody Leave leave){
      Leave appliedLeave = leaveService.save(leave);
      return appliedLeave;
    }

    @GetMapping("/leave/{leaveId}")
    public Leave getLeave(@PathVariable Long leaveId){
        Leave leave = leaveService.findById(leaveId);
        return leave;
    }

    @GetMapping("/employees/leave-info/{leaveInfoId}")
    public LeaveInfo getLeaveInfo(@PathVariable Long leaveInfoId){
        LeaveInfo leaveInfo = leaveService.findLeaveInfoById(leaveInfoId);
        return leaveInfo;
    }

    @PostMapping("/employees/apply-leave")
    public String applyLeave(@RequestBody ApplyLeaveDto applyLeaveDto){
        return leaveService.applyLeave(applyLeaveDto);
    }

    //Leaves for admin
    @GetMapping("/employees/applied-leaves")
    public List<AppliedLeaveDto> getAppliedLeavesForAdmin(){
        return leaveService.getAppliedLeavesForAdmin();
    }

    //approve leaves
    @GetMapping("/employees/leave/approve/{leaveId}")
    public String approveLeave(@PathVariable Long leaveId){
        leaveService.approveLeave(leaveId);
        return "Leave approved successfully";
    }
}
