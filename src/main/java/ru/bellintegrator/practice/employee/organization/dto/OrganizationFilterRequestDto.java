package ru.bellintegrator.practice.employee.organization.dto;

import lombok.Data;

/**
 * Organization with filtering data DTO request
 */
@Data
public class OrganizationFilterRequestDto {
    /**
     * name of Organization
     */
    private String name;
    /**
     * inn of Organization
     */
    private String inn;
    /**
     * Is the organization in operation (is_active = true / false)
     */
    private Boolean isActive;
}


