package ru.bellintegrator.practice.employee.directory.service;

import ru.bellintegrator.practice.employee.directory.dto.TypeDocDto;

import java.util.List;

/**
 * Service
 */
public interface TypeDocService {

    /**
     * Get Guides of Doc by specific attributes (name, code)
     *
     * @param typeDocDto
     * @return List<CountryDto>
     */
    List<TypeDocDto> getByParams(TypeDocDto typeDocDto);
}
