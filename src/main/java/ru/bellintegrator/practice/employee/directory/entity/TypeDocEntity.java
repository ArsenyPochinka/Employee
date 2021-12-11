package ru.bellintegrator.practice.employee.directory.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Type of doc
 */
@Data
@Entity(name = "Type_Doc")
public class TypeDocEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

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

    public TypeDocEntity(String name) {
        this.name = name;
    }

    public TypeDocEntity(String name, String code) {
        this.name = name;
        this.code = code;
    }


    public TypeDocEntity() {

    }
}