package ru.bellintegrator.practice.employee.person.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PersonSaveDto {

    @NotNull(message = "officeId cannot be null")
    private Integer officeId;

    @Size(max = 50)
    @NotEmpty(message = "firstName cannot be null")
    private String firstName;

    @Size(max = 50)
    private String secondName;

    @Size(max = 50)
    private String middleName;

    @Size(max = 50)
    @NotEmpty(message = "post cannot be null")
    private String post;

    @Size(max = 16)
    private String phone;

    private String docCode;

    private String docName;

    @Size(max = 12)
    private String docNumber;

    @Size(max = 10)
    private String docDate;

    @Size(max = 10)
    private String[] citizenshipCodes;

    private Boolean isIdentified;

    public PersonSaveDto(Integer officeId, String firstName, String secondName, String middleName, String post, String phone, String docCode, String docName, String docNumber, String docDate, @Size(max = 10) String[] citizenshipCodes, Boolean isIdentified) {
        this.officeId = officeId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.post = post;
        this.phone = phone;
        this.docCode = docCode;
        this.docName = docName;
        this.docNumber = docNumber;
        this.docDate = docDate;
        this.citizenshipCodes = citizenshipCodes;
        this.isIdentified = isIdentified;
    }
}
