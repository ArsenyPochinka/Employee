package ru.bellintegrator.practice.employee.organization.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.employee.organization.dto.*;
import ru.bellintegrator.practice.employee.organization.service.OrganizationService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Controller class for working with organizations
 */
@RestController
@RequestMapping(value = "/api/organization", produces = APPLICATION_JSON_VALUE)
public class OrganizationController {

    private final OrganizationService organizationService;

    /**
     * Constructor
     *
     * @param organizationService (a service that provides methods of working with organizations)
     */
    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    /**
     * Returns a filtered list of organizations
     *
     * @param filter (filter for the list)
     * @return filtered list
     */
    @ApiOperation(value = "Get organization's list by filter", nickname = "getOrganizationListById", httpMethod = "POST")
    @PostMapping("/list")
    public List<OrganizationFilterResponseDto> list(@RequestBody OrganizationFilterRequestDto filter) {
        return organizationService.list(filter);
    }

    /**
     * Returns the organization with the specified ID
     *
     * @param id (organization id)
     * @return the organization with the specified id
     */
    @ApiOperation(value = "Get organization by id", nickname = "getOrganizationById", httpMethod = "GET")
    @GetMapping("/{id}")
    public OrganizationDto getById(@PathVariable Integer id) {
        return organizationService.getById(id);
    }

    /**
     * Updates information about the organization
     *
     * @param updateOrganization (an object containing information to update)
     */
    @ApiOperation(value = "Update organization", nickname = "updateOrganization", httpMethod = "POST")
    @PostMapping("/update")
    public void update(@RequestBody OrganizationUpdateDto updateOrganization) {
        organizationService.update(updateOrganization);
    }

    /**
     * Saves information about the new organization
     *
     * @param saveOrganization (an object containing information about the new organization)
     */
    @ApiOperation(value = "Save organization", nickname = "saveOrganization", httpMethod = "POST")
    @PostMapping("/save")
    public void save(@RequestBody OrganizationSaveDto saveOrganization) {
        organizationService.save(saveOrganization);
    }
}
