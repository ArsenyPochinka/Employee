package ru.bellintegrator.practice.employee.guide.entity;

import lombok.Data;
import ru.bellintegrator.practice.employee.person.entity.Person;

import javax.persistence.*;

/**
 * Doc
 */
@Data
@Entity(name = "Doc")
public class Doc {

    @Id
    @Column(name = "person_id")
    private Integer id;

    /**
     * Unique type of doc identifier
     */
    @Version
    private Integer version;

    /**
     * number of doc
     */
    @Column(name = "doc_number", length = 12, nullable = false)
    private String docNumber;

    /**
     * date of doc
     */
    @Column(name = "doc_date", length = 10, nullable = false)
    private String docDate;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_type_id")
    private TypeDoc typeDoc;
}