package ru.bellintegrator.practice.employee.user.dto;

import lombok.Data;

/**
 * User with filtering data DTO response
 */
@Data
public class UserFilterResponseDto {
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
}
