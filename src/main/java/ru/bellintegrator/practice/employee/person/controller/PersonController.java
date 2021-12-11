package ru.bellintegrator.practice.employee.person.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.employee.person.dto.*;
import ru.bellintegrator.practice.employee.person.service.PersonService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/person", produces = APPLICATION_JSON_VALUE)
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{id}")
    public PersonDto getById(@PathVariable Integer id) {
        return personService.getById(id);
    }

    @PostMapping("/list")
    public List<PersonListResponseDto> getParamsOrganization(@RequestBody PersonListRequestDto request) {
        return personService.getByParams(request);
    }

    @PostMapping("/update")
    public void update(@RequestBody PersonUpdateDto request) {
        personService.update(request);
    }

    @PostMapping("/save")
    public void save(@RequestBody PersonSaveDto request) {
        personService.add(request);
    }


}
