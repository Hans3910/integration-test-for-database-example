package com.switchfully.databasedemowithintegrationtest.service;

import com.switchfully.databasedemowithintegrationtest.repository.Employee;
import com.switchfully.databasedemowithintegrationtest.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;


@SpringBootTest
class EmployeeServiceIntegrationTest {
    @MockBean
    EmployeeRepository mockRepository;

    @Autowired
    EmployeeService employeeService;

    @Test
    void whenIGetAllTheServantsFromTheService_thenTHere4() {
        // Given
        Mockito.when(mockRepository.findAll()).thenReturn(List.of(new Employee(1, "abc")));
        // When
        List<Employee> switchfullyServants = employeeService.getSwitchfullyServants();

        // Then
        Assertions.assertThat(switchfullyServants).hasSize(1);
    }
}