package ru.bellintegrator.practice.employee.office.dto;

import lombok.Data;

/**
 * Office to save DTO
 */
@Data
public class OfficeSaveDto {
    /**
     * Unique organization identifier
     */
    private Integer orgId;
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
