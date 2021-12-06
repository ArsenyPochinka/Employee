package ru.bellintegrator.practice.employee.organization.dto;

import lombok.Data;

import javax.validation.constraints.*;
    @Data
    public class RequestParamsOrganizationDto {

        @Size(max = 50)
        @NotEmpty(message = "name cannot be null")
        public String name;

        @Size(max = 12)
        public String inn;

        public boolean isActive;

        @Override
        public String toString() {
            return "{" +
                    "name='" + name + '\'' +
                    ", inn='" + inn + '\'' +
                    ", isActive=" + isActive +
                    '}';
        }
    }

