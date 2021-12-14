package ru.bellintegrator.practice.employee.organization.service;

import ru.bellintegrator.practice.employee.organization.dto.*;

import java.util.List;

/**
 * An interface that provides methods for working with organizations
 */
public interface OrganizationService {

    /**
     * Returns a filtered list of organizations
     *
     * @param filter (filter for the list)
     * @return filtered list
     */
    List<OrganizationFilterResponseDto> list(OrganizationFilterRequestDto filter);

    /**
     * Returns the organization with the specified ID
     *
     * @param id (organization id)
     * @return the organization with the specified id
     */
    OrganizationDto getById(Integer id);

    /**
     * Updates information about the organization
     *
     * @param updateOrganization (object containing information to update)
     */
    void update(OrganizationUpdateDto updateOrganization);

    /**
     * Saves information about the new organization
     *
     * @param newOrganization (an object containing information about the new organization)
     */
    void save(OrganizationSaveDto newOrganization);
}
