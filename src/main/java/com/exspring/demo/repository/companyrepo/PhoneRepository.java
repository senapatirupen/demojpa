package com.exspring.demo.repository.companyrepo;

import com.exspring.demo.entity.companyentity.Employee;
import com.exspring.demo.entity.companyentity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
