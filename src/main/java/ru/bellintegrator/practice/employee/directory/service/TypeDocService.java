package ru.bellintegrator.practice.employee.directory.service;

import ru.bellintegrator.practice.employee.directory.dto.TypeDocDto;

import java.util.List;

/**
 * Service for type's doc
 */
public interface TypeDocService {
    /**
     * Returns a list of type's doc
     *
     * @return list of type's doc
     */
    List<TypeDocDto> list();
}
