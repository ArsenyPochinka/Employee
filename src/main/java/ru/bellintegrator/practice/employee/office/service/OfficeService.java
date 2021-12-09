package ru.bellintegrator.practice.employee.office.service;

import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.practice.employee.office.dto.OfficeWithIdDto;
import ru.bellintegrator.practice.employee.office.dto.OfficeWithOrgIdDto;
import ru.bellintegrator.practice.employee.office.dto.ResponseParamsOfficeDto;
import ru.bellintegrator.practice.employee.office.dto.RequestParamsOfficeDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Service
 */
@Validated
public interface OfficeService {

    /**
     * Get Offices with specific attributes (id, name, isActive)
     * by parameters organizationId, name, phone, isActive
     *
     * @param requestParamsOfficeDto
     * @return List<ResponseParamsOfficeDto>
     */
    List<ResponseParamsOfficeDto> getByParams(@Valid RequestParamsOfficeDto requestParamsOfficeDto);

    /**
     * Get Office by id
     *
     * @param id
     * @return OfficeWithIdDto
     */
    OfficeWithIdDto getById(@NotNull Integer id);

    /**
     * update Office by id
     *
     * @param officeWithIdDto
     */
    void update(@Valid OfficeWithIdDto officeWithIdDto);

    /**
     * add new Office in DB
     *
     * @param officeWithOrgIdDto
     */
    void add(@Valid OfficeWithOrgIdDto officeWithOrgIdDto);
}
