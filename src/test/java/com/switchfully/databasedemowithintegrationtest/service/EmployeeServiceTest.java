package com.switchfully.databasedemowithintegrationtest.service;

import com.switchfully.databasedemowithintegrationtest.repository.Employee;
import com.switchfully.databasedemowithintegrationtest.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

/**
 * Unit test, using mocking
 */

class EmployeeServiceTest {

    private EmployeeService employeeService;
    private EmployeeRepository mockEmployeeRepository; // mock!

    @BeforeEach
    void setup() {
        mockEmployeeRepository = Mockito.mock(EmployeeRepository.class);
        employeeService = new EmployeeService(mockEmployeeRepository);
    }


    @Test
    void whenRetrievenAllEmployees_thenTheRepositoryIsCalledBothOnFIndAllAndFindAllByManagerNulll() {
        // Test the interaction (using a mock)
        // Given

        // When
        employeeService.getSwitchfullyServants();
        // Then
        Mockito.verify(mockEmployeeRepository).findAll();
        Mockito.verify(mockEmployeeRepository).findAllByManagerNull();
    }

    @Test
    void whenRetrievingAllServants_thenReinoutIsNotInTheList() {
        // Given
        Employee reinout = new Employee(1, "Reinout");
        Employee karel = new Employee(2, "Karel");
        // AND THE CONFIGURED STUB
        Mockito.when(mockEmployeeRepository.findAll())
                .thenReturn(List.of(reinout, karel));
        Mockito.when(mockEmployeeRepository.findAllByManagerNull())
                .thenReturn(List.of(reinout));


        // When
        List<Employee> result = employeeService.getSwitchfullyServants();
        // Then
        Assertions.assertThat(result).isNotEmpty();
        Assertions.assertThat(result).doesNotContain(reinout);
        Assertions.assertThat(result).contains(karel);
    }

    @Test
    void givenListOfEmployees_retunsEmployeesWithoutManagers() {
        // Given
        Employee reinout = new Employee(1, "Reinout");
        Employee karel = new Employee(2, "Karel");
        List<Employee> employees = List.of(reinout, karel);
        List<Employee> managers = List.of(reinout);
        // When
        List<Employee> result = employeeService.extractManagersFromEmployees(employees, managers);
        // Then
        Assertions.assertThat(result).isNotEmpty();
        Assertions.assertThat(result).doesNotContain(reinout);
        Assertions.assertThat(result).contains(karel);
    }
}