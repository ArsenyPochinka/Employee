package ru.bellintegrator.practice.employee.directory.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class CountryDto {

    @Size(max = 50)
    private String name;

    @Size(max = 10)
    private String code;

    public CountryDto(String name, String code) {
        this.name = name;
        this.code = code;
    }
}
