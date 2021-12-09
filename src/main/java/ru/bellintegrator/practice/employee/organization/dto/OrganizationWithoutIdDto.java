package ru.bellintegrator.practice.employee.organization.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class OrganizationWithoutIdDto {

    @Size(max = 50)
    @NotEmpty(message = "name cannot be null")
    private String name;

    @Size(max = 50)
    @NotEmpty(message = "full name cannot be null")
    private String fullName;

    @Size(max = 12)
    @NotEmpty(message = "inn cannot be null")
    private String inn;

    @Size(max = 9)
    @NotEmpty(message = "kpp cannot be null")
    private String kpp;

    @Size(max = 50)
    @NotEmpty(message = "address cannot be null")
    private String address;

    @Size(max = 16)
    private String phone;

    private Boolean isActive;

    public OrganizationWithoutIdDto(String name, String fullName, String inn, String kpp, String address, String phone, Boolean isActive) {
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }
}
