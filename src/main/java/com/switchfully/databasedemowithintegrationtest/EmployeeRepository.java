package com.switchfully.databasedemowithintegrationtest;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    List<Employee> findByFirstNameStartsWith(String startLetter);
    List<Employee> findAllByManagerNull();
}
