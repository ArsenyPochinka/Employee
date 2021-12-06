package ru.bellintegrator.practice.employee.organization.dto;

import lombok.Data;

import javax.validation.constraints.*;
@Data
public class OrganizationDto {

    public Integer id;

    @Size(max = 50)
    @NotEmpty(message = "name cannot be null")
    public String name;

    @Size(max = 50)
    @NotEmpty(message = "full name cannot be null")
    public String fullName;

    @Size(max = 12)
    @NotEmpty(message = "inn cannot be null")
    public String inn;

    @Size(max = 9)
    @NotEmpty(message = "kpp cannot be null")
    public String kpp;

    @Size(max = 50)
    @NotEmpty(message = "address cannot be null")
    public String address;

    @Size(max = 16)
    public String phone;

    public boolean isActive;

    public OrganizationDto(Integer id, String name, String fullName, String inn, String kpp, String address, String phone, boolean isActive) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", inn='" + inn + '\'' +
                ", kpp='" + kpp + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
