package ru.bellintegrator.practice.employee.office.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class OfficeWithIdDto {

    @NotNull(message = "id cannot be null")
    private Integer id;

    @Size(max = 50)
    @NotEmpty(message = "name cannot be null")
    private String name;

    @Size(max = 50)
    @NotEmpty(message = "address cannot be null")
    private String address;

    @Size(max = 16)
    private String phone;

    private Boolean isActive;

    public OfficeWithIdDto(Integer id, String name, String address, String phone, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }
}
