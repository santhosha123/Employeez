package com.demo.employeez.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String firstname;
    private String lastname;
    @Column(nullable = false)
    private String email;
    private LocalDate dateOfBirth;
    private LocalDate joinedOn;
    private String designation;
    private String address;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Long mobile;
    private String photoUrl;
    @OneToOne
    private LeaveInfo leaveInfo;
    @OneToMany(mappedBy = "employee")
    @JsonManagedReference
    private List<Leave> leaves;
    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonBackReference
    private Department department;
    @Column
    private int reportTo;
    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonBackReference
    Role role;
    @ManyToOne
    @JoinColumn(name = "team_id")
    @JsonBackReference
    private Team team;
}
