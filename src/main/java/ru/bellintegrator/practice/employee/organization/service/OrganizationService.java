package ru.bellintegrator.practice.employee.organization.service;

import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.practice.employee.organization.dto.OrganizationDto;
import ru.bellintegrator.practice.employee.organization.dto.RequestParamsOrganizationDto;
import ru.bellintegrator.practice.employee.organization.dto.ResponseParamsOrganizationDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Сервис
 */
@Validated
public interface OrganizationService {

    /**
     * Get specific Organization's attributes (id, name, isActive)
     * by parameters name, inn, isActive
     *
     * @param requestParamsOrganizationDto
     * @return
     */
    ResponseParamsOrganizationDto getParamsOrganization(@Valid RequestParamsOrganizationDto requestParamsOrganizationDto);

    /**
     * Get Organization by id
     *
     * @param id
     * @return
     */
    OrganizationDto getById(@NotNull Integer id);

    /**
     * update Organization by id
     *
     * @param organizationDto
     */
    void update(@Valid OrganizationDto organizationDto);

    /**
     * add new Organization in DB
     *
     * @param organizationDto
     */
    void add(@Valid OrganizationDto organizationDto);
}
