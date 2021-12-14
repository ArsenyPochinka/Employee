package ru.bellintegrator.practice.employee.directory.service;

import ru.bellintegrator.practice.employee.directory.dto.CountryDto;

import java.util.List;

/**
 * Service for country
 */
public interface CountryService {
    /**
     * Returns a list of countries and their codes
     *
     * @return list of countries and their codes
     */
    List<CountryDto> list();
}
