package ru.bellintegrator.practice.employee.guide.entity;

import ru.bellintegrator.practice.employee.person.entity.Person;

import javax.persistence.*;

/**
 * Doc
 */
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public String getDocDate() {
        return docDate;
    }

    public void setDocDate(String docDate) {
        this.docDate = docDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public TypeDoc getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(TypeDoc typeDoc) {
        this.typeDoc = typeDoc;
    }
}