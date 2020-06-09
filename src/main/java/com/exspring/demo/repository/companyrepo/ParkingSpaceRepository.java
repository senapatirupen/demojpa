package com.exspring.demo.repository.companyrepo;

import com.exspring.demo.entity.companyentity.Employee;
import com.exspring.demo.entity.companyentity.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Long> {
}
