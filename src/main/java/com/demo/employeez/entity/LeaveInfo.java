package com.demo.employeez.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LeaveInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int sick;
    private int earned;
    private int paternity;

    public LeaveInfo(int sick, int earned, int paternity) {
        this.sick = sick;
        this.earned = earned;
        this.paternity = paternity;
    }
}
