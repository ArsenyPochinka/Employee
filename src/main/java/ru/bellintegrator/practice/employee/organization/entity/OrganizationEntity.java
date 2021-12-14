package ru.bellintegrator.practice.employee.organization.entity;

import ru.bellintegrator.practice.employee.office.entity.OfficeEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Organization Entity
 */
@Entity(name = "Organization")
public class OrganizationEntity {
    /**
     * Unique organization identifier
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
     * name of Organization
     */
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /**
     * full name  of Organization
     */
    @Column(name = "full_name", length = 50, nullable = false)
    private String fullName;

    /**
     * inn of Organization
     */
    @Column(name = "inn", length = 12, nullable = false)
    private String inn;

    /**
     * kpp of Organization
     */
    @Column(name = "kpp", length = 9, nullable = false)
    private String kpp;

    /**
     * address of Organization
     */
    @Column(name = "address", length = 50, nullable = false)
    private String address;

    /**
     * phone  of Organization
     */
    @Column(name = "phone", length = 20)
    private String phone;

    /**
     * Is the organization in operation (is_active = true / false)
     */
    @Column(name = "is_active")
    private Boolean isActive;

    /**
     * List of organization's offices
     */
    @OneToMany(
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JoinColumn(name = "org_id")
    private List<OfficeEntity> offices;

    /**
     * Constructor for Hibernate
     */
    public OrganizationEntity() {
    }

    /**
     * Конструктор
     *
     * @param name     name of Organization
     * @param fullName full name of Organization
     * @param inn      inn of Organization
     * @param kpp      kpp of Organization
     * @param address  address of Organization
     * @param phone    phone of Organization
     * @param isActive Is the organization in operation (is_active = true / false)
     */
    public OrganizationEntity(String name, String fullName, String inn, String kpp, String address, String phone, Boolean isActive) {
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public List<OfficeEntity> getOffices() {
        return offices;
    }

    public void setOffices(List<OfficeEntity> offices) {
        this.offices = offices;
    }
}