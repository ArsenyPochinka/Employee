package ru.bellintegrator.practice.employee.person.dto;

import lombok.Data;

@Data
public class PersonListResponseDto {

    private Integer id;

    private String firstName;

    private String secondName;

    private String middleName;

    private String post;

    public PersonListResponseDto(Integer id, String firstName, String secondName, String middleName, String post) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.post = post;
    }
}
