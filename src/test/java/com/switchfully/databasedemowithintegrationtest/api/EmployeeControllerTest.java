package com.switchfully.databasedemowithintegrationtest.api;


import com.switchfully.databasedemowithintegrationtest.repository.Employee;
import com.switchfully.databasedemowithintegrationtest.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EmployeeService employeeService;

    @Test
    void whenICallTheAPIforEmployees_thenIllgetAllTheServants() throws Exception {
        // Given

        // When
        mockMvc.perform(get("/employees")).andExpect(status().isOk());
        // Then
    }

    @Test
    void whenIcallABogusURL_thingsWillFail() throws Exception {
        // Given

        // When
        mockMvc.perform(get("/abc")).andExpect(status().isNotFound());
        // Then
    }
}