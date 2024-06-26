package com.demo.employeez.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeamDto {
    private int id;
    private String name;
}
