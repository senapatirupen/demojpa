package com.exspring.demo.repository.companyrepo;

import com.exspring.demo.entity.companyentity.Award;
import com.exspring.demo.entity.companyentity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AwardRepository extends JpaRepository<Award, Long> {
}
