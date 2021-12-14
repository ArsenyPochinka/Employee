package ru.bellintegrator.practice.employee.directory.entity;

import javax.persistence.*;

/**
 * Type of doc Entity
 */
@Entity(name = "Type_Doc")
public class TypeDocEntity {
    /**
     * Unique type of doc identifier
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
     * type doc's name
     */
    @Column(name = "name", length = 50)
    private String name;

    /**
     * type doc's code
     */
    @Column(name = "code", length = 10)
    private String code;
    /**
     * Constructor for Hibernate
     */
    public TypeDocEntity() {

    }
    /**
     * Constructor Type of doc
     *
     * @param name         name's type of doc
     * @param code         code's type of doc
     */
    public TypeDocEntity(String name, String code) {
        this.name = name;
        this.code = code;
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