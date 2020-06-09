package com.exspring.demo.repository.companyrepo;

import com.exspring.demo.entity.companyentity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e.name FROM Employee e WHERE e.id = :id")
    public String findEmployeeNameById(Long id);
}
