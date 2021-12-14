package ru.bellintegrator.practice.employee.user.dto;

import lombok.Data;

/**
 * User with filtering data DTO request
 */
@Data
public class UserFilterRequestDto {
    /**
     * Unique office identifier
     */
    private Integer officeId;
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
     * doc's code of User
     */
    private String docCode;
    /**
     * country's code of User
     */
    private String citizenshipCode;
}


