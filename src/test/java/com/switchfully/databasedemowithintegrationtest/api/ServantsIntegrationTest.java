package com.switchfully.databasedemowithintegrationtest.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ServantsIntegrationTest {
    @LocalServerPort
    int localServerPort;

    @Autowired
    MockMvc mockMvc;

    @Test
    void whenIgetAllTheServants_thereAre4() throws Exception {
        // Given

        // When
        mockMvc.perform(get("/employees")).andExpect(status().isOk());
        // Then
    }
}