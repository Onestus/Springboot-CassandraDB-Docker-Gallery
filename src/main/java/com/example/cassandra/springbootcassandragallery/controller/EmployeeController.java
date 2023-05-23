package com.example.cassandra.springbootcassandragallery.controller;

import com.example.cassandra.springbootcassandragallery.ResourceNotFoundException;
import com.example.cassandra.springbootcassandragallery.model.Employee;
import com.example.cassandra.springbootcassandragallery.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.module.ResolutionException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        employeeRepository.save(employee);
        return employee;
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> findById(@PathVariable("id") Integer employeeId){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResolutionException("Employee not found" + employeeId)
        );
        return ResponseEntity.ok().body(employee);
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    @PutMapping("employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Integer employeeId,
                                                       @RequestBody Employee employeeDetails){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee not found for this id: " + employeeId)
        );
        employee.setName(employeeDetails.getName());
        employee.setSurname(employeeDetails.getSurname());
        employee.setJob(employee.getJob());
        final Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("employees/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable(value = "id") Integer employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee not found for this id: " + employeeId)
        );
        employeeRepository.delete(employee);
        return ResponseEntity.ok().build();
    }
}
