package ru.bellintegrator.practice.employee.office.dto;

import lombok.Data;

/**
 * Office with filtering data DTO request
 */
@Data
public class OfficeFilterRequestDto {
    /**
     * Unique organization identifier
     */
    private Integer orgId;
    /**
     * name of Office
     */
    private String name;
    /**
     * phone  of Office
     */
    private String phone;
    /**
     * Is the office in operation (is_active = true / false)
     */
    private Boolean isActive;
}


