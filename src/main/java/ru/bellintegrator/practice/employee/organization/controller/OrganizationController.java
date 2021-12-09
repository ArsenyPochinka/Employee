package ru.bellintegrator.practice.employee.organization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.employee.organization.dto.*;
import ru.bellintegrator.practice.employee.organization.service.OrganizationService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/organization", produces = APPLICATION_JSON_VALUE)
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping("/{id}")
    public OrganizationDto getById(@PathVariable Integer id) {
        return organizationService.getById(id);
    }

    @PostMapping("/list")
    public List<ResponseParamsOrganizationDto> getParamsOrganization(@RequestBody RequestParamsOrganizationDto request) {
        return organizationService.getByParams(request);
    }

    @PostMapping("/update")
    public void update(@RequestBody OrganizationDto request) {
        organizationService.update(request);
    }

    @PostMapping("/save")
    public void save(@RequestBody OrganizationWithoutIdDto request) {
        organizationService.add(request);
    }


}
