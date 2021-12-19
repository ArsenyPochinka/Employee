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
@Sql(value = {"/clear.sql", "/create-organization.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/clear.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@AutoConfigureMockMvc
@Transactional
public class OrganizationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testList_withCorrectDataFilter_thenOrganizationsShouldBeFound_thenStatus200() throws Exception {
        String jsonBody = "{\"name\": \"MTS\"}";
        mockMvc.perform(post("/api/organization/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].id", is(3)))
                .andExpect(jsonPath("$.data[0].name", is("MTS")))
                .andExpect(jsonPath("$.data[0].isActive", is(true)));
    }

    @Test
    public void testList_withCorrectDataFilter_thenOrganizationsShouldNotBeFound_thenStatus200() throws Exception {
        String jsonBody = "{\"name\": \"Defunct company\"}";
        mockMvc.perform(post("/api/organization/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(0)));
    }

    @Test
    public void testList_withIncorrectDataFilter_thenStatus400() throws Exception {
        String jsonBody = "{}";
        mockMvc.perform(post("/api/organization/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.error", notNullValue()));
    }

    @Test
    public void testGetById_withExistingID_thenOrganizationShouldBeFound_thenStatus200() throws Exception {
        mockMvc.perform(get("/api/organization/4"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data.name", is("Beeline")))
                .andExpect(jsonPath("$.data.fullName", is("Bee Line GSM")))
                .andExpect(jsonPath("$.data.inn", is("323098123785")))
                .andExpect(jsonPath("$.data.kpp", is("323095721")))
                .andExpect(jsonPath("$.data.address", is("Moscow, Polynskay street, 12.")))
                .andExpect(jsonPath("$.data.phone", is("+7(912)445-23-88")))
                .andExpect(jsonPath("$.data.isActive", is(true)));
    }

    @Test
    public void testGetById_withNonExistingID_thenOrganizationShouldNotBeFound_thenStatus400() throws Exception {
        mockMvc.perform(get("/api/organization/100"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error", notNullValue()))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    public void testUpdate_withCorrectDataUpdate_thenTheOrganizationShouldBeUpdate_thenStatus200() throws Exception {
        String jsonBody = "{" +
                "\"id\": 3," +
                "\"name\": \"Megafon\"," +
                "\"fullName\": \"Megafon Mobile Operator\"," +
                "\"inn\": \"723098123016\"," +
                "\"kpp\": \"123098158\"," +
                "\"address\": \"Moscow, Alekseevskay street, 1a.\"," +
                "\"phone\": \"+7(956)445-23-32\"," +
                "\"isActive\": true" +
                "}";
        mockMvc.perform(post("/api/organization/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("success")));
    }

    @Test
    public void testUpdate_withIncorrectDataUpdate_thenTheOrganizationShouldNotBeUpdate_thenStatus400() throws Exception {
        String jsonBody = "{" +
                "\"id\": 100," +
                "\"name\": \"Megafon\"," +
                "\"fullName\": \"Megafon Mobile Operator\"," +
                "\"inn\": \"723098123016\"," +
                "\"kpp\": \"123098158\"," +
                "\"address\": \"Moscow, Altufevo street, 1a.\"" +
                "}";
        mockMvc.perform(post("/api/organization/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.error", notNullValue()));
    }

    @Test
    public void testSave_withCorrectDataSave_thenNewOrganizationShouldBeAdd_thenStatus200() throws Exception {
        String jsonBody = "{" +
                "\"name\": \"Megafon\"," +
                "\"fullName\": \"Megafon Mobile Operator\"," +
                "\"inn\": \"723098123016\"," +
                "\"kpp\": \"123098158\"," +
                "\"address\": \"Moscow, Altufevo street, 1a.\"," +
                "\"phone\": \"+7(956)445-23-32\"," +
                "\"isActive\": true" +
                "}";
        mockMvc.perform(post("/api/organization/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result", is("success")));
    }

    @Test
    public void testSave_withIncorrectDataSave_thenNewOrganizationShouldNotBeAdd_thenStatus400() throws Exception {
        String jsonBody = "{" +
                "\"fullName\": \"Megafon Mobile Operator\"," +
                "\"inn\": \"723098123016\"," +
                "\"kpp\": \"123098158\"," +
                "\"address\": \"Moscow, Altufevo street, 1a.\"," +
                "\"phone\": \"+7(956)445-23-32\"," +
                "\"isActive\": true" +
                "}";
        mockMvc.perform(post("/api/organization/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.error", notNullValue()));
    }
}
