package com.exspring.demo.repository.companyrepo;

import com.exspring.demo.entity.companyentity.Address;
import com.exspring.demo.entity.companyentity.Hierarchy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HierarchyRepository extends JpaRepository<Hierarchy, Long> {
}
