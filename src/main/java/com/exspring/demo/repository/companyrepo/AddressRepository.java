package com.exspring.demo.repository.companyrepo;

import com.exspring.demo.entity.companyentity.Address;
import com.exspring.demo.entity.companyentity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
