package com.demo.employeez.mapper;

import com.demo.employeez.dto.TeamDto;
import com.demo.employeez.entity.Team;

public class TeamMapper {
    public static TeamDto mapToTeamDto(Team team){
        TeamDto teamDto = TeamDto.builder()
                .id(team.getId())
                .name(team.getName())
                .build();
        return teamDto;
    }
}
