package ru.bellintegrator.practice.employee.office.dto;

import lombok.Data;

/**
 * Office DTO
 */
@Data
public class OfficeDto {
    /**
     * Unique office identifier
     */
    private Integer id;
    /**
     * name of Office
     */
    private String name;
    /**
     * address of Office
     */
    private String address;
    /**
     * phone  of Office
     */
    private String phone;
    /**
     * Is the office in operation (is_active = true / false)
     */
    private Boolean isActive;
}
