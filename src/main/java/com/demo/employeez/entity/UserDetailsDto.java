package com.demo.employeez.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsDto implements UserDetails {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private LocalDate dateOfBirth;
    private LocalDate joinedOn;
    private String designation;
    private String address;
    private String password;
    private Long mobile;
    private String photoUrl;
    private LeaveInfo leaveInfo;
    private List<Leave> leaves;
    private Department department;
    private int reportTo;
    private Role role;
    private List<GrantedAuthority> authorities;
    private Boolean active;

    public UserDetailsDto(Employee employee){
        this.id = employee.getId();
        this.firstname = employee.getFirstname();
        this.lastname = employee.getLastname();
        this.email = employee.getEmail();
        this.dateOfBirth = employee.getDateOfBirth();
        this.joinedOn = employee.getJoinedOn();
        this.designation = employee.getDesignation();
        this.address = employee.getAddress();
        this.password = employee.getPassword();
        this.mobile = employee.getMobile();
        this.photoUrl = employee.getPhotoUrl();
        this.leaveInfo = employee.getLeaveInfo();
        this.leaves = employee.getLeaves();
        this.department = employee.getDepartment();
        this.role = employee.getRole();
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        this.role = employee.getRole();
        authorities.add(new SimpleGrantedAuthority(role.getName()));
        this.authorities=authorities;
        this.active = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
