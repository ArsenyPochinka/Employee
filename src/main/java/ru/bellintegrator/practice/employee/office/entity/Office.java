package ru.bellintegrator.practice.employee.office.entity;


import ru.bellintegrator.practice.employee.person.entity.Person;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Office
 */
@Entity(name = "Office")
public class Office {

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
     * Unique organization identifier
     */
    @Column(name = "organization_id", nullable = false)
    private Integer organizationId;

    /**
     * name
     */
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /**
     * address
     */
    @Column(name = "address", length = 50, nullable = false)
    private String address;

    /**
     * phone
     */
    @Column(name = "phone", length = 16)
    private String phone;

    /**
     * is active = 1 is true, 0 is false
     */
    @Column(name = "is_active")
    private boolean isActive;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "office_id")
    Set<Person> persons;

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

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Set<Person> getPersons() {
        if(persons == null) {
            persons = new HashSet<>();
        }
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }
}