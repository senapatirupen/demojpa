package com.exspring.demo.repository.companyrepo;

import com.exspring.demo.entity.companyentity.Employee;
import com.exspring.demo.entity.companyentity.OfficeLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeLocationRepository extends JpaRepository<OfficeLocation, Long> {
}
