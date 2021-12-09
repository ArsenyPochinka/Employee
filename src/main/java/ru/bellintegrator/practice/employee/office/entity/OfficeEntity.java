package ru.bellintegrator.practice.employee.office.entity;


import lombok.Data;
import ru.bellintegrator.practice.employee.person.entity.Person;

import javax.persistence.*;
import java.util.Set;

/**
 * Office
 */
@Data
@Entity(name = "Office")
public class OfficeEntity {

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
    @Column(name = "name", length = 50)
    private String name;

    /**
     * address
     */
    @Column(name = "address", length = 50)
    private String address;

    /**
     * phone
     */
    @Column(name = "phone", length = 16)
    private String phone;

    /**
     * is active = true / false
     */
    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "office_id")
    private Set<Person> persons;

    public OfficeEntity(String name, String address, String phone, Boolean isActive) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

    public OfficeEntity(Integer organizationId, String name, String address, String phone, Boolean isActive) {
        this.organizationId = organizationId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }



    public OfficeEntity() {

    }
}