package ru.bellintegrator.practice.employee.organization.dto;

import lombok.Data;

import javax.validation.constraints.*;
    @Data
    public class RequestParamsOrganizationDto {

        @Size(max = 50)
        @NotEmpty(message = "name cannot be null")
        private String name;

        @Size(max = 12)
        private String inn;

        private Boolean isActive;
    }

