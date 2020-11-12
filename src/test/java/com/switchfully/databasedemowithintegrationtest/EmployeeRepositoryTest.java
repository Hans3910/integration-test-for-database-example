package com.switchfully.databasedemowithintegrationtest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
//@SpringBootTest
class EmployeeRepositoryTest {
    @Autowired
    EmployeeRepository repository;

    @Test
    void testSetup() {
    }

    @Test
    void whenIGetAllTheEmployeesFromTheDB_thenIhave5() {
        // Given

        // When
        List<Employee> employees = new ArrayList<>();
        for (Employee employee : repository.findAll()) {
            employees.add(employee);
        }

        // Then
        assertThat(employees).hasSize(5);
    }

    @Test
    void whenIGetEmployeeWithID3_theNameIsKarel() {
        // Given

        // When
        Employee result = repository.findById(3).get();
        // Then
        assertThat(result.getFirstName()).isEqualTo("Karel");
    }

    @Test
    void whenISearchForEmployeesStartingWithK_thenIGet2() {
        // Given

        // When
        List<Employee> results = repository.findByFirstNameStartsWith("R");
        List<String> firstNames = results.stream().map(emp -> emp.getFirstName()).collect(Collectors.toList());
        // Then
        assertThat(firstNames).containsAnyOf("Reinout", "Kobe");
    }

    @Test
    void whenISearchForEmployeesWithoutManager_iOnlyGetReinout() {
        // Given

        // When
        List<Employee> noManager = repository.findAllByManagerNull();
        Employee reinout = noManager.get(0);
        // Then
        assertThat(noManager).hasSize(1);
        assertThat(reinout.getFirstName()).isEqualTo("Reinout");
    }

    @Test
    @Sql("promote-karel.sql")
    void whenPromoteKarel_thenThereAre2ManagersAtSwitchfully() {
        // Given

        // When
        List<Employee> noManager = repository.findAllByManagerNull();
        // Then
        assertThat(noManager).hasSize(2);
    }
}