package ru.bellintegrator.practice.employee.person.entity;

import ru.bellintegrator.practice.employee.guide.entity.Country;
import ru.bellintegrator.practice.employee.guide.entity.Doc;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Person
 */
@Entity(name = "Person")
public class Person {

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
     * firstname
     */
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    /**
     * second name
     */
    @Column(name = "second_name", length = 50)
    private String secondName;

    /**
     * third name
     */
    @Column(name = "third_name", length = 50)
    private String thirdName;

    /**
     * post
     */
    @Column(name = "post", length = 50, nullable = false)
    private String post;

    /**
     * phone
     */
    @Column(name = "phone", length = 16)
    private String phone;

    /**
     * is identified = 1 is true, 0 is false
     */
    @Column(name = "is_identified")
    private boolean isIdentified;

    @OneToOne(
            mappedBy = "person",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            optional = false
    )
    private Doc doc;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "Person_Country",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id")
    )
    private Set<Country> countries;

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

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isIdentified() {
        return isIdentified;
    }

    public void setIdentified(boolean identified) {
        isIdentified = identified;
    }

    public Doc getDoc() {
        return doc;
    }

    public void setDoc(Doc doc) {
        this.doc = doc;
    }

    public Set<Country> getCountries() {
        if(countries == null) {
            countries = new HashSet<>();
        }
        return countries;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }
}