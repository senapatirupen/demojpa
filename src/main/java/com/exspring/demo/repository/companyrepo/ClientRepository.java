package com.exspring.demo.repository.companyrepo;

import com.exspring.demo.entity.companyentity.Address;
import com.exspring.demo.entity.companyentity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
