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
}