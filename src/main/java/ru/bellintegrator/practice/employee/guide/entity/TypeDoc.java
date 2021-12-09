package ru.bellintegrator.practice.employee.guide.entity;

import lombok.Data;
import ru.bellintegrator.practice.employee.person.entity.Person;

import javax.persistence.*;
import java.util.Set;

/**
 * Type of doc
 */
@Data
@Entity(name = "Type_Doc")
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

}