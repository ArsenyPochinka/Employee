package ru.bellintegrator.practice.employee.organization.dto;

import lombok.Data;

/**
 * Organization with filtering data DTO response
 */
@Data
public class OrganizationFilterResponseDto {
    /**
     * Unique organization identifier
     */
    private Integer id;
    /**
     * name of Organization
     */
    private String name;
    /**
     * Is the organization in operation (is_active = true / false)
     */
    private Boolean isActive;
}
