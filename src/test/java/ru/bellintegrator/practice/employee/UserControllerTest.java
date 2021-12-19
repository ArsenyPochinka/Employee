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
@Sql(value = {"/clear.sql", "/create-organization.sql", "/create-office.sql", "/create-country.sql", "/create-typeDoc.sql",
              "/create-user.sql", "/create-doc.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/clear.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@AutoConfigureMockMvc
@Transactional
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testList_withCorrectDataFilter_thenUsersShouldBeFound_thenStatus200() throws Exception {
        String jsonBody = "{\"officeId\": 3}";
        mockMvc.perform(post("/api/user/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.data[0].id", is(3)))
                .andExpect(jsonPath("$.data[0].firstName", is("Kirsenko")))
                .andExpect(jsonPath("$.data[0].lastName", is("Konstantin")))
                .andExpect(jsonPath("$.data[0].position", is("manager")))
                .andExpect(jsonPath("$.data[1].id", is(4)))
                .andExpect(jsonPath("$.data[1].firstName", is("Pochinka")))
                .andExpect(jsonPath("$.data[1].lastName", is("Arseny")))
                .andExpect(jsonPath("$.data[1].position", is("provider")));
    }

    @Test
    public void testList_withCorrectDataFilter_thenUsersShouldNotBeFound_thenStatus200() throws Exception {
        String jsonBody = "{\"officeId\": 100}";
        mockMvc.perform(post("/api/user/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(0)));
    }

    @Test
    public void testList_withIncorrectDataFilter_thenStatus400() throws Exception {
        String jsonBody = "{}";
        mockMvc.perform(post("/api/user/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.error", notNullValue()));
    }

    @Test
    public void testGetById_withExistingID_thenUserShouldBeFound_thenStatus200() throws Exception {
        mockMvc.perform(get("/api/user/5"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data.id", is(5)))
                .andExpect(jsonPath("$.data.firstName", is("Khafizov")))
                .andExpect(jsonPath("$.data.lastName", is("Timur")))
                .andExpect(jsonPath("$.data.position", is("doctor")))
                .andExpect(jsonPath("$.data.phone", is("+7(912)345-23-12")))
                .andExpect(jsonPath("$.data.docName", is("Passport of a citizen of Ukraine")))
                .andExpect(jsonPath("$.data.docNumber", is("49 23 547834")))
                .andExpect(jsonPath("$.data.docDate", is("2015-01-12")))
                .andExpect(jsonPath("$.data.citizenshipName", is("Ukraine")))
                .andExpect(jsonPath("$.data.citizenshipCode", is("254")))
                .andExpect(jsonPath("$.data.isIdentified", is(true)));
    }

    @Test
    public void testGetById_withNonExistingID_thenUserShouldNotBeFound_thenStatus400() throws Exception {
        mockMvc.perform(get("/api/user/100"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error", notNullValue()))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    public void testUpdate_withCorrectDataUpdate_thenTheUserShouldBeUpdate_thenStatus200() throws Exception {
        String jsonBody = "{" +
                "\"id\": 5," +
                "\"officeId\": 4," +
                "\"firstName\": \"Bob\"," +
                "\"lastName\": \"Marley\"," +
                "\"position\": \"musician\"," +
                "\"phone\": \"89030231234\"," +
                "\"docName\": \"Passport USA\"," +
                "\"docNumber\": \"123\"," +
                "\"docDate\": \"2021-12-16\"," +
                "\"citizenshipCode\": \"1\"," +
                "\"isIdentified\": true" +
                "}";
        mockMvc.perform(post("/api/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is("success")));
    }

    @Test
    public void testUpdate_withIncorrectDataUpdate_thenTheUserShouldNotBeUpdate_thenStatus400() throws Exception {
        String jsonBody = "{" +
                "\"id\": 100," +
                "\"officeId\": 4," +
                "\"firstName\": \"Bob\"," +
                "\"lastName\": \"Marley\"," +
                "\"position\": \"musician\"," +
                "\"phone\": \"89030231234\"," +
                "\"docName\": \"Passport USA\"," +
                "\"docNumber\": \"1234 123123\"," +
                "\"docDate\": \"2021-12-16\"," +
                "\"citizenshipCode\": \"1\"," +
                "\"isIdentified\": true" +
                "}";
        mockMvc.perform(post("/api/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.error", notNullValue()));
    }

    @Test
    public void testSave_withCorrectDataSave_thenNewUserShouldBeAdd_thenStatus200() throws Exception {
        String jsonBody = "{" +
                "\"officeId\": 4," +
                "\"firstName\": \"Bob\"," +
                "\"lastName\": \"Marley\"," +
                "\"position\": \"musician\"," +
                "\"phone\": \"89030231234\"," +
                "\"docCode\": \"100\"," +
                "\"docName\": \"Passport USA\"," +
                "\"docNumber\": \"1234 123123\"," +
                "\"docDate\": \"2021-12-16\"," +
                "\"citizenshipCode\": \"1\"," +
                "\"isIdentified\": true" +
                "}";
        mockMvc.perform(post("/api/user/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result", is("success")));
    }

    @Test
    public void testSave_withIncorrectDataSave_thenNewOfficeShouldNotBeAdd_thenStatus400() throws Exception {
        String jsonBody = "{" +
                "\"officeId\": 100," +
                "\"firstName\": \"Bob\"," +
                "\"lastName\": \"Marley\"," +
                "\"position\": \"musician\"," +
                "\"phone\": \"89030231234\"," +
                "\"docCode\": \"100\"," +
                "\"docName\": \"Passport USA\"," +
                "\"docNumber\": \"1234 123123\"," +
                "\"docDate\": \"2021-12-16\"," +
                "\"citizenshipCode\": \"1\"," +
                "\"isIdentified\": true" +
                "}";
        mockMvc.perform(post("/api/user/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.error", notNullValue()));
    }
}
