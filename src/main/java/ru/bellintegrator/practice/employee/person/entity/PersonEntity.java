package ru.bellintegrator.practice.employee.person.entity;

import lombok.Data;
import ru.bellintegrator.practice.employee.directory.entity.CountryEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * Person
 */
@Data
@NamedQueries({
        @NamedQuery(
                name = "Person.getById",
                query = "SELECT p FROM Person p WHERE p.id= :id"
        )
})
@Entity(name = "Person")
public class PersonEntity {

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
    @Column(name = "middle_name", length = 50)
    private String middleName;

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
     * is identified = true / false
     */
    @Column(name = "is_identified")
    private Boolean isIdentified;

    @OneToOne(
            mappedBy = "person",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            optional = false
    )
    @PrimaryKeyJoinColumn
    private DocEntity doc;

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
    private Set<CountryEntity> countries;

    public PersonEntity(Integer officeId, String firstName, String secondName, String middleName, String post, String phone, Boolean isIdentified, DocEntity doc, Set<CountryEntity> countries) {
        this.officeId = officeId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.post = post;
        this.phone = phone;
        this.isIdentified = isIdentified;
        this.doc = doc;
        this.countries = countries;
    }

    public PersonEntity(String firstName, String secondName, String middleName, String post, String phone, Boolean isIdentified, DocEntity doc, Set<CountryEntity> countries) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.post = post;
        this.phone = phone;
        this.isIdentified = isIdentified;
        this.doc = doc;
        this.countries = countries;
    }

    public PersonEntity() {

    }
}