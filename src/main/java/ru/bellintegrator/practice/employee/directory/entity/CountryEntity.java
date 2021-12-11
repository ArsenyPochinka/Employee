package ru.bellintegrator.practice.employee.directory.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Country
 */
@Data
@Entity(name = "Country")
public class CountryEntity {

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
    @Column(name = "name", length = 50)
    private String name;

    /**
     * code
     */
    @Column(name = "code", length = 10)
    private String code;

    public CountryEntity(String code) {
        this.code = code;
    }

    public CountryEntity(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public CountryEntity() {

    }
}