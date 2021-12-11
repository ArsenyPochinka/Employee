package ru.bellintegrator.practice.employee.person.dto;

import lombok.Data;
import ru.bellintegrator.practice.employee.directory.dto.CountryDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PersonDto {

    @NotNull(message = "officeId cannot be null")
    private Integer id;

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

    @Size(max = 50)
    private String docName;

    @Size(max = 12)
    private String docNumber;

    @Size(max = 10)
    private String docDate;

    private CountryDto[] countries;

    private Boolean isIdentified;

    public PersonDto(Integer id, String firstName, String secondName, String middleName, String post, String phone, String docName, String docNumber, String docDate, CountryDto[] countries, Boolean isIdentified) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.post = post;
        this.phone = phone;
        this.docName = docName;
        this.docNumber = docNumber;
        this.docDate = docDate;
        this.countries = countries;
        this.isIdentified = isIdentified;
    }
}
