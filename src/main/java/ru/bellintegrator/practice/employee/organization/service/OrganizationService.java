package ru.bellintegrator.practice.employee.organization.service;

import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.practice.employee.organization.dto.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Service
 */
@Validated
public interface OrganizationService {

    /**
     * Get Organizations with specific attributes (id, name, isActive)
     * by parameters name, inn, isActive
     *
     * @param requestParamsOrganizationDto
     * @return List<ResponseParamsOrganizationDto>
     */
    List<ResponseParamsOrganizationDto> getByParams(@Valid RequestParamsOrganizationDto requestParamsOrganizationDto);

    /**
     * Get Organization by id
     *
     * @param id
     * @return OrganizationDto
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
     * @param organizationWithoutIdDto
     */
    void add(@Valid OrganizationWithoutIdDto organizationWithoutIdDto);
}
