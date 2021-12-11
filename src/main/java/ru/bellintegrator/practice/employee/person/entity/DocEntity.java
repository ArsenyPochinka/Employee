package ru.bellintegrator.practice.employee.person.entity;

import lombok.Data;
import ru.bellintegrator.practice.employee.directory.entity.TypeDocEntity;

import javax.persistence.*;

/**
 * Doc
 */
@Data
@Entity(name = "Doc")
public class DocEntity {

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
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_type_id")
    private TypeDocEntity typeDoc;

    public DocEntity(String docNumber, String docDate, TypeDocEntity typeDoc) {
        this.docNumber = docNumber;
        this.docDate = docDate;
        this.typeDoc = typeDoc;
    }

    public DocEntity() {

    }
}