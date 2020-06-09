package com.exspring.demo.entity.companyentity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"employees"})
@EqualsAndHashCode(exclude = {"employees"})

@Entity
public class Department extends AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "department")
    private Collection<Employee> employees;

    public Department addEmployee(Employee employee) {
        if (this.employees == null) {
            this.employees = new HashSet<Employee>();
        }
        this.employees.add(employee);
        return this;
    }

    public Department addAllEmployees(Collection<Employee> employees) {
        if (this.employees == null) {
            this.employees = new HashSet<Employee>();
        }
        this.employees.addAll(employees);
        return this;
    }
}
