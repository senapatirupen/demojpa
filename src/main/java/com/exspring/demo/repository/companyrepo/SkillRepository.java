package com.exspring.demo.repository.companyrepo;

import com.exspring.demo.entity.companyentity.Address;
import com.exspring.demo.entity.companyentity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}
