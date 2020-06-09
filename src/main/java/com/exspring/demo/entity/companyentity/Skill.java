package com.exspring.demo.entity.companyentity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Skill extends AuditLog{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(name="SKILL_EMP",
            joinColumns=@JoinColumn(name="SKILL_ID"),
            inverseJoinColumns=@JoinColumn(name="EMP_ID"))
    @MapKeyColumn(name="SK_ID")
    private Map<String, Employee> employeesBySkill;

}
