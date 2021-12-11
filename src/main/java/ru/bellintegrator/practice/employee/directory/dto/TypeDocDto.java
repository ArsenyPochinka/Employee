package ru.bellintegrator.practice.employee.directory.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class TypeDocDto {

    private Integer id;

    @Size(max = 50)
    private String name;

    @Size(max = 10)
    private String code;

    public TypeDocDto(Integer id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }
}
