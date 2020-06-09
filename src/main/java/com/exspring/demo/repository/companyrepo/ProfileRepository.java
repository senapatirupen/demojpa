package com.exspring.demo.repository.companyrepo;

import com.exspring.demo.entity.companyentity.Employee;
import com.exspring.demo.entity.companyentity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
