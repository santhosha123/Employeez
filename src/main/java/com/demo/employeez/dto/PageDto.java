package com.demo.employeez.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageDto {
    private int offset;
    private int itemsPerPage;
    private String field;
    private String direction;
}
