package com.demo.employeez.repository;

import com.demo.employeez.entity.Employee;
import com.demo.employeez.entity.Role;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT E FROM Employee E WHERE E.firstname LIKE CONCAT('%',:query,'%')")
    List<Employee> searchEmployees(String query, Pageable pageable);
    @Query("SELECT E FROM Employee E WHERE E.firstname LIKE CONCAT('%',:query,'%')")
    List<Employee> searchEmployees(String query);
    Employee findByEmail(String username);

    @Query("SELECT E FROM Employee  E WHERE MONTH(E.dateOfBirth) = MONTH(CURRENT_DATE) AND DAY(E.dateOfBirth) >= DAY(CURRENT_DATE)")
    List<Employee> getEmployeeByBirthday(Sort dateOfBirth);

    List<Employee> findByRole(Role role);
}
