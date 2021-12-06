package ru.bellintegrator.practice.employee.organization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.employee.organization.dto.OrganizationDto;
import ru.bellintegrator.practice.employee.organization.dto.RequestParamsOrganizationDto;
import ru.bellintegrator.practice.employee.organization.dto.ResponseParamsOrganizationDto;
import ru.bellintegrator.practice.employee.organization.service.OrganizationService;

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
    public ResponseParamsOrganizationDto getParamsOrganization(@RequestBody RequestParamsOrganizationDto request) {
        return organizationService.getParamsOrganization(request);
    }

    @PostMapping("/update")
    public void update(@RequestBody OrganizationDto request) {
        organizationService.update(request);
    }

    @PostMapping("/save")
    public void save(@RequestBody OrganizationDto request) {
        organizationService.add(request);
    }


}
