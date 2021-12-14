package ru.bellintegrator.practice.employee.office.dto;

import lombok.Data;

/**
 * Office with filtering data DTO response
 */
@Data
public class OfficeFilterResponseDto {
    /**
     * Unique office identifier
     */
    private Integer id;
    /**
     * name of Office
     */
    private String name;
    /**
     * Is the office in operation (is_active = true / false)
     */
    private Boolean isActive;
}
