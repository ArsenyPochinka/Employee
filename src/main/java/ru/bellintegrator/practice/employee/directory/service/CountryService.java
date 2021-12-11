package ru.bellintegrator.practice.employee.directory.service;

import ru.bellintegrator.practice.employee.directory.dto.CountryDto;

import java.util.List;

/**
 * Service
 */
public interface CountryService {

    /**
     * Get all Guides of Country
     *
     * @param
     * @return List<CountryDto>
     */
    List<CountryDto> all();
}
