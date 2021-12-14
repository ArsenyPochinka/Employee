package ru.bellintegrator.practice.employee.user.entity;

import ru.bellintegrator.practice.employee.directory.entity.TypeDocEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Doc Entity
 */
@Entity(name = "Doc")
public class DocEntity {
    /**
     * Unique user and doc's user identifier
     */
    @Id
    @Column(name = "user_id")
    private Integer id;

    /**
     * Service field hibernate
     */
    @Version
    private Integer version;

    /**
     * number of doc
     */
    @Column(name = "doc_number", length = 12)
    private String docNumber;

    /**
     * date of doc
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "doc_date")
    private Date docDate;
    /**
     * User's doc
     */
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private UserEntity user;
    /**
     * Type of doc
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_type_id")
    private TypeDocEntity typeDoc;

    /**
     * Constructor for Hibernate
     */
    public DocEntity() {

    }
    /**
     * Constructor Doc
     *
     * @param id           unique user and doc's user identifier
     * @param docDate      date of doc
     * @param typeDoc      type of doc
     */
    public DocEntity(Integer id, String docNumber, Date docDate, TypeDocEntity typeDoc) {
        this.id = id;
        this.docNumber = docNumber;
        this.docDate = docDate;
        this.typeDoc = typeDoc;
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

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public TypeDocEntity getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(TypeDocEntity typeDoc) {
        this.typeDoc = typeDoc;
    }
}