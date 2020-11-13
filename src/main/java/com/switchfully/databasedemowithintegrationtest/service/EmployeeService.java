package com.switchfully.databasedemowithintegrationtest.service;

import com.switchfully.databasedemowithintegrationtest.repository.Employee;
import com.switchfully.databasedemowithintegrationtest.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;


    @Autowired
    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getSwitchfullyServants(){
        List<Employee> listOfEmployees = new ArrayList<>();

        repository.findAll().forEach(listOfEmployees::add);
        List<Employee> managers = repository.findAllByManagerNull();

        return extractManagersFromEmployees(listOfEmployees, managers);
    }

    public List<Employee> extractManagersFromEmployees(List<Employee> employees, List<Employee> managers) {
        List<Employee> result = new ArrayList<>(employees);
        result.removeAll(managers);

        return result;
    }
}
