package ru.bellintegrator.practice.employee.organization.entity;

import lombok.Data;
import ru.bellintegrator.practice.employee.office.entity.OfficeEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * Organization
 */
@Data
@Entity(name = "Organization")
public class OrganizationEntity {
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
     * name
     */
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /**
     * full name
     */
    @Column(name = "full_name", length = 50, nullable = false)
    private String fullName;

    /**
     * inn
     */
    @Column(name = "inn", length = 12, nullable = false)
    private String inn;

    /**
     * kpp
     */
    @Column(name = "kpp", length = 9, nullable = false)
    private String kpp;

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
     * is active = true / false
     */
    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JoinColumn(name = "organization_id")
    private Set<OfficeEntity> officeEntities;

    public OrganizationEntity(Integer id, String name, String fullName, String inn, String kpp, String address, String phone, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

    public OrganizationEntity(String name, String fullName, String inn, String kpp, String address, String phone, Boolean isActive) {
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

    public OrganizationEntity() {
    }
}