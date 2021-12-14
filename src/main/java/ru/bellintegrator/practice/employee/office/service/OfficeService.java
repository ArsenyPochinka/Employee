package ru.bellintegrator.practice.employee.office.service;

import ru.bellintegrator.practice.employee.office.dto.*;

import java.util.List;

/**
 * An interface that provides methods for working with offices
 */
public interface OfficeService {

    /**
     * Returns a filtered list of offices
     *
     * @param filter (filter for the list)
     * @return filtered list
     */
    List<OfficeFilterResponseDto> list(OfficeFilterRequestDto filter);

    /**
     * Returns the office with the specified ID
     *
     * @param id (office id)
     * @return the office with the specified id
     */
    OfficeDto getById(Integer id);

    /**
     * Updates information about the office
     *
     * @param updateOffice (object containing information to update)
     */
    void update(OfficeUpdateDto updateOffice);

    /**
     * Saves information about the new office
     *
     * @param newOffice (an object containing information about the new office)
     */
    void save(OfficeSaveDto newOffice);
}
