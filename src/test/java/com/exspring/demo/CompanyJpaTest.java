package com.exspring.demo;

import com.exspring.demo.entity.companyentity.Employee;
import com.exspring.demo.repository.companyrepo.EmployeeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
public class CompanyJpaTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    // write test cases here
    @Test
    public void whenFindByName_thenReturnEmployee() {
        // given
        Employee employee = Employee.builder().id(1L).name("Rupen").build();
        employeeRepository.save(employee);

        // when
        Employee found = employeeRepository.findById(1L).get();

        // then
        log.info(found.getName());
        displayObjectAsJson(found);

    }

    private void displayObjectAsJson(Object obj) {
        try {
            log.info("object as json >>>>> {}", new ObjectMapper().writeValueAsString(obj));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
    }

}