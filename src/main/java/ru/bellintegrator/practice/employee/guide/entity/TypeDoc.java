package ru.bellintegrator.practice.employee.guide.entity;

import javax.persistence.*;

/**
 * Type of doc
 */
@Entity(name = "TypeDoc")
public class TypeDoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

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