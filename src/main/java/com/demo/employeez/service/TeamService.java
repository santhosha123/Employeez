package com.demo.employeez.service;

import com.demo.employeez.dto.TeamDto;

import java.util.List;

public interface TeamService {
    List<TeamDto> findAll();
}
