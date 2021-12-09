package ru.bellintegrator.practice.employee.office.dto;

import lombok.Data;

@Data
public class ResponseParamsOfficeDto {

    private Integer id;

    private String name;

    private Boolean isActive;

    public ResponseParamsOfficeDto(Integer id, String name, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
    }
}
