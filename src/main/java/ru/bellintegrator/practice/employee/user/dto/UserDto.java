package ru.bellintegrator.practice.employee.user.dto;

import lombok.Data;

import java.util.Date;

/**
 * User DTO
 */
@Data
public class UserDto {
    /**
     * Unique user identifier
     */
    private Integer id;
    /**
     * firstname of User
     */
    private String firstName;
    /**
     * last name of User
     */
    private String lastName;
    /**
     * middle name of User
     */
    private String middleName;
    /**
     * position of User
     */
    private String position;
    /**
     * phone  of User
     */
    private String phone;
    /**
     * doc's name of User
     */
    private String docName;
    /**
     * doc's number of User
     */
    private String docNumber;
    /**
     * doc's date of User
     */
    private Date docDate;
    /**
     * country's name of User
     */
    private String citizenshipName;
    /**
     * country's code of User
     */
    private String citizenshipCode;
    /**
     * Is the user identified (identified = true / false)
     */
    private Boolean isIdentified;
}
