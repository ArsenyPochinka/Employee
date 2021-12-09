package ru.bellintegrator.practice.employee.organization.dto;

import lombok.Data;

@Data
public class ResponseParamsOrganizationDto {

    private Integer id;

    private String name;

    private Boolean isActive;

    public ResponseParamsOrganizationDto(Integer id, String name, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
    }
}
