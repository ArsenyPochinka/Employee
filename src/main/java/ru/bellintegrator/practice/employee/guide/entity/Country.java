package ru.bellintegrator.practice.employee.guide.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Country
 */
@Data
@Entity(name = "Country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * Service field hibernate
     */
    @Version
    private Integer version;

    /**
     * name
     */
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /**
     * code
     */
    @Column(name = "code", length = 10, nullable = false)
    private String code;
}