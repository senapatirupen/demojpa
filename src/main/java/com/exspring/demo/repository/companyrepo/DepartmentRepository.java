package com.exspring.demo.repository.companyrepo;

import com.exspring.demo.entity.companyentity.Department;
import com.exspring.demo.entity.companyentity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
