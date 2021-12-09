package ru.bellintegrator.practice.employee.office.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class OfficeWithOrgIdDto {

    @NotNull(message = "id cannot be null")
    private Integer organizationId;

    @Size(max = 50)
    private String name;

    @Size(max = 50)
    private String address;

    @Size(max = 16)
    private String phone;

    private Boolean isActive;

    public OfficeWithOrgIdDto(Integer organizationId, String name, String address, String phone, Boolean isActive) {
        this.organizationId = organizationId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }
}
