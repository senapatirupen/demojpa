package com.exspring.demo.repository.companyrepo;

import com.exspring.demo.entity.companyentity.EmergencyContact;
import com.exspring.demo.entity.companyentity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyContactRepository extends JpaRepository<EmergencyContact, Long> {
}
