package ru.bellintegrator.practice.employee.user.entity;

import ru.bellintegrator.practice.employee.directory.entity.CountryEntity;

import javax.persistence.*;

/**
 * User Entity
 */
@Entity(name = "User")
public class UserEntity {
    /**
     * Unique user identifier
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
     * Unique office identifier
     */
    @Column(name = "office_id", nullable = false)
    private Integer officeId;

    /**
     * firstname of user
     */
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    /**
     * second name of user
     */
    @Column(name = "last_name", length = 50)
    private String lastName;

    /**
     * third name of user
     */
    @Column(name = "middle_name", length = 50)
    private String middleName;

    /**
     * position of user
     */
    @Column(name = "position", length = 50, nullable = false)
    private String position;

    /**
     * phone of user
     */
    @Column(name = "phone", length = 16)
    private String phone;

    /**
     * Is the user identified (identified = true / false)
     */
    @Column(name = "is_identified")
    private Boolean isIdentified;

    /**
     * Doc's user
     */
    @OneToOne(
            mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            optional = false
    )
    @PrimaryKeyJoinColumn
    private DocEntity doc;
    /**
     * Doc's country
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private CountryEntity country;

    /**
     * Constructor for Hibernate
     */
    public UserEntity() {

    }
    /**
     * Constructor User
     *
     * @param officeId     unique Organization identifier
     * @param firstName    firstname of User
     * @param lastName     last name of User
     * @param middleName   middle name of User
     * @param position     position of User
     * @param phone        phone of User
     * @param isIdentified Is the user identified (identified = true / false)
     * @param doc          document of User
     * @param country      country of User
     */
    public UserEntity(Integer officeId, String firstName, String lastName, String middleName, String position, String phone, Boolean isIdentified, DocEntity doc, CountryEntity country) {
        this.officeId = officeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.position = position;
        this.phone = phone;
        this.isIdentified = isIdentified;
        this.doc = doc;
        this.country = country;
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

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String secondName) {
        this.lastName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getIdentified() {
        return isIdentified;
    }

    public void setIdentified(Boolean identified) {
        isIdentified = identified;
    }

    public DocEntity getDoc() {
        return doc;
    }

    public void setDoc(DocEntity doc) {
        this.doc = doc;
    }

    public CountryEntity getCountry() {
        return country;
    }

    public void setCountry(CountryEntity country) {
        this.country = country;
    }
}