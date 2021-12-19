package ru.bellintegrator.practice.employee;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EmployeeApplication.class})
@TestPropertySource("/application-test.properties")
@Sql(value = {"/clear.sql", "/create-organization.sql", "/create-office.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/clear.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@AutoConfigureMockMvc
@Transactional
public class OfficeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testList_withCorrectDataFilter_thenOfficesShouldBeFound_thenStatus200() throws Exception {
        String jsonBody = "{\"orgId\": 3}";
        mockMvc.perform(post("/api/office/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.data[0].id", is(3)))
                .andExpect(jsonPath("$.data[0].name", is("MTS General Office")))
                .andExpect(jsonPath("$.data[0].isActive", is(true)))
                .andExpect(jsonPath("$.data[1].id", is(4)))
                .andExpect(jsonPath("$.data[1].name", is("MTS Second Office")))
                .andExpect(jsonPath("$.data[1].isActive", is(true)));
    }


    @Test
    public void testList_withCorrectDataFilter_thenOfficesShouldNotBeFound_thenStatus200() throws Exception {
        String jsonBody = "{\"orgId\": 100}";
        mockMvc.perform(post("/api/office/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(0)));
    }

    @Test
    public void testList_withIncorrectDataFilter_thenStatus400() throws Exception {
        String jsonBody = "{}";
        mockMvc.perform(post("/api/office/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.error", notNullValue()));
    }

    @Test
    public void testGetById_withExistingID_thenOfficeShouldBeFound_thenStatus200() throws Exception {
        mockMvc.perform(get("/api/office/5"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data.id", is(5)))
                .andExpect(jsonPath("$.data.name", is("Beeline General Office")))
                .andExpect(jsonPath("$.data.address", is("Moscow, Severnay street, 15.")))
                .andExpect(jsonPath("$.data.phone", is("+7(999)345-23-22")))
                .andExpect(jsonPath("$.data.isActive", is(true)));
    }

    @Test
    public void testGetById_withNonExistingID_thenOfficeShouldNotBeFound_thenStatus400() throws Exception {
        mockMvc.perform(get("/api/office/100"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error", notNullValue()))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    public void testUpdate_withCorrectDataUpdate_thenTheOfficeShouldBeUpdate_thenStatus200() throws Exception {
        String jsonBody = "{" +
                "\"id\": 5," +
                "\"name\": \"Beeline Second Office\"," +
                "\"address\": \"Moscow, Severnay street, 15.\"," +
                "\"phone\": \"+7(999)345-23-22\"," +
                "\"isActive\": true" +
                "}";
        mockMvc.perform(post("/api/office/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("success")));
    }

    @Test
    public void testUpdate_withIncorrectDataUpdate_thenTheOfficeShouldNotBeUpdate_thenStatus400() throws Exception {
        String jsonBody = "{" +
                "\"id\": 100," +
                "\"name\": \"Beeline Second Office\"," +
                "\"address\": \"Moscow, Severnay street, 15.\"," +
                "\"phone\": \"+7(999)345-23-22\"," +
                "\"isActive\": true" +
                "}";
        mockMvc.perform(post("/api/office/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.error", notNullValue()));
    }

    @Test
    public void testSave_withCorrectDataSave_thenNewOfficeShouldBeAdd_thenStatus200() throws Exception {
        String jsonBody = "{" +
                "\"orgId\": 4," +
                "\"name\": \"Beeline Second Office\"," +
                "\"address\": \"Moscow, Severnay street, 15.\"," +
                "\"phone\": \"+7(999)345-23-22\"," +
                "\"isActive\": true" +
                "}";
        mockMvc.perform(post("/api/office/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result", is("success")));
    }

    @Test
    public void testSave_withIncorrectDataSave_thenNewOfficeShouldNotBeAdd_thenStatus400() throws Exception {
        String jsonBody = "{" +
                "\"orgId\": 100," +
                "\"name\": \"Beeline Second Office\"," +
                "\"address\": \"Moscow, Severnay street, 15.\"," +
                "\"phone\": \"+7(999)345-23-22\"," +
                "\"isActive\": true" +
                "}";
        mockMvc.perform(post("/api/office/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.error", notNullValue()));
    }
}
