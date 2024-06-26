package com.demo.employeez.service.impl;

import com.demo.employeez.repository.TeamRepository;
import com.demo.employeez.service.TeamService;
import com.demo.employeez.dto.TeamDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.demo.employeez.mapper.TeamMapper.mapToTeamDto;

@Service
public class TeamServiceImpl implements TeamService {
    TeamRepository teamRepository;
    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<TeamDto> findAll() {
        List<TeamDto> teams = teamRepository.findAll().stream()
                .map(team -> mapToTeamDto(team))
                .collect(Collectors.toList());
        return teams;
    }
}
