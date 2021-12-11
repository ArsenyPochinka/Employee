package ru.bellintegrator.practice.employee.person.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PersonListRequestDto {

    @NotNull(message = "officeId cannot be null")
    private Integer officeId;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String secondName;

    @Size(max = 50)
    private String middleName;

    @Size(max = 50)
    private String post;

    @Size(max = 10)
    private String docCode;

    @Size(max = 10)
    private String[] citizenshipCodes;

    public PersonListRequestDto(Integer officeId, String firstName, String secondName, String middleName, String post, String docCode, @Size(max = 10) String[] citizenshipCodes) {
        this.officeId = officeId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.post = post;
        this.docCode = docCode;
        this.citizenshipCodes = citizenshipCodes;
    }
}
