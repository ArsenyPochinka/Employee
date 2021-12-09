package ru.bellintegrator.practice.employee.office.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class RequestParamsOfficeDto {

    @NotNull(message = "id cannot be null")
    private Integer organizationId;

    @Size(max = 50)
    private String name;

    @Size(max = 16)
    private String phone;

    private Boolean isActive;
}

