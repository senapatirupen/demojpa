package com.exspring.demo.restapi;

import com.exspring.demo.entity.companyentity.*;
import com.exspring.demo.repository.companyrepo.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@RestController
public class EmployeeMgmtController {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AwardRepository awardRepository;

    @Autowired
    EmergencyContactRepository emergencyContactRepository;

    @Autowired
    OfficeLocationRepository officeLocationRepository;

    @Autowired
    ParkingSpaceRepository parkingSpaceRepository;

    @Autowired
    PhoneRepository phoneRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    ProjectRepository projectRepository;

    @PostMapping(value = "/addDepartmentToEmployee")
    public ResponseEntity<Employee> addDepartmentToEmployee(@RequestParam Long employeeId, @RequestParam Long departmentId) {
        Employee employee = employeeRepository.findById(employeeId).get();
        Department department = departmentRepository.findById(departmentId).get();
        department.addEmployee(employee);
        departmentRepository.save(department);
        employee.setDepartment(department);
        Employee updatedEmployee = employeeRepository.save(employee);
        displayObjectAsJson(employee);
        displayObjectAsJson(department);
        return new ResponseEntity<Employee>(updatedEmployee, HttpStatus.CREATED);
    }

    @GetMapping(value = "/findDepartmentFromEmployeeById")
    public ResponseEntity<Department> findDepartmentFromEmployeeById(@RequestParam Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();
        displayObjectAsJson(employee);
        return new ResponseEntity<Department>(employee.getDepartment(), HttpStatus.OK);
    }

    @PostMapping(value = "/createEmployee")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        employeeRepository.save(employee);
        displayObjectAsJson(employee);
        return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
    }

    @GetMapping(value = "/getEmployee")
    public ResponseEntity<Employee> getEmployee(@RequestParam Long id) {
        Employee employee = employeeRepository.findById(id).get();
        displayObjectAsJson(employee);
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }


    @PostMapping(value = "/createDepartment")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        departmentRepository.save(department);
        displayObjectAsJson(department);
        return new ResponseEntity<Department>(department, HttpStatus.CREATED);
    }

    @PostMapping(value = "/createAddress")
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        addressRepository.save(address);
        displayObjectAsJson(address);
        return new ResponseEntity<Address>(address, HttpStatus.CREATED);
    }

    @PostMapping(value = "/createAward")
    public ResponseEntity<Award> createAward(@RequestBody Award award) {
        awardRepository.save(award);
        displayObjectAsJson(award);
        return new ResponseEntity<Award>(award, HttpStatus.CREATED);
    }

    @PostMapping(value = "/createEmergencyContact")
    public ResponseEntity<EmergencyContact> createEmergencyContact(@RequestBody EmergencyContact emergencyContact) {
        emergencyContactRepository.save(emergencyContact);
        displayObjectAsJson(emergencyContact);
        return new ResponseEntity<EmergencyContact>(emergencyContact, HttpStatus.CREATED);
    }

    @PostMapping(value = "/createOfficeLocation")
    public ResponseEntity<OfficeLocation> createOfficeLocation(@RequestBody OfficeLocation officeLocation) {
        officeLocationRepository.save(officeLocation);
        displayObjectAsJson(officeLocation);
        return new ResponseEntity<OfficeLocation>(officeLocation, HttpStatus.CREATED);
    }

    @PostMapping(value = "/createParkingSpace")
    public ResponseEntity<ParkingSpace> createParkingSpace(@RequestBody ParkingSpace parkingSpace) {
        parkingSpaceRepository.save(parkingSpace);
        displayObjectAsJson(parkingSpace);
        return new ResponseEntity<ParkingSpace>(parkingSpace, HttpStatus.CREATED);
    }

    @PostMapping(value = "/createPhone")
    public ResponseEntity<Phone> createPhone(@RequestBody Phone phone) {
        phoneRepository.save(phone);
        displayObjectAsJson(phone);
        return new ResponseEntity<Phone>(phone, HttpStatus.CREATED);
    }

    @PostMapping(value = "/createProfile")
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile) {
        profileRepository.save(profile);
        displayObjectAsJson(profile);
        return new ResponseEntity<Profile>(profile, HttpStatus.CREATED);
    }

    @PostMapping(value = "/createProject")
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        projectRepository.save(project);
        displayObjectAsJson(project);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }

    private void displayObjectAsJson(Object obj) {
        try {
            log.info("object as json >>>>> {}", objectMapper.writeValueAsString(obj));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
    }
}
