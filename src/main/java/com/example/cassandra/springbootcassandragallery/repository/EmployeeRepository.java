package com.example.cassandra.springbootcassandragallery.repository;

import com.example.cassandra.springbootcassandragallery.model.Employee;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface EmployeeRepository extends CassandraRepository<Employee, Integer> {
}
