package ru.bellintegrator.practice.employee.directory.entity;

import javax.persistence.*;

/**
 * Country Entity
 */
@Entity(name = "Country")
public class CountryEntity {
    /**
     * Unique country identifier
     */
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
     * name of country
     */
    @Column(name = "name", length = 50)
    private String name;

    /**
     * code of country
     */
    @Column(name = "code", length = 10)
    private String code;
    /**
     * Constructor for Hibernate
     */
    public CountryEntity() {

    }
    /**
     * Getter and setter methods
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}