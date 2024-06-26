package com.demo.employeez.controller;

import com.demo.employeez.dto.TeamDto;
import com.demo.employeez.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class TeamController {
    TeamService teamService;
    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/teams")
    public List<TeamDto> getAllTeams(){
        List<TeamDto> teams = teamService.findAll();
        return teams;
    }
}
