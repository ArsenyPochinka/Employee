package ru.bellintegrator.practice.employee.organization.entity;

import ru.bellintegrator.practice.employee.office.entity.Office;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Organization
 */
@Entity(name = "Organization")
public class Organization {
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
     * is active = 1 is true, 0 is false
     */
    @Column(name = "is_active")
    private boolean isActive;

    @OneToMany(
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JoinColumn(name = "organization_id")
    private Set<Office> offices;

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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Set<Office> getOffices() {
        if(offices == null) {
            offices = new HashSet<>();
        }
        return offices;
    }

    public void setOffices(Set<Office> offices) {
        this.offices = offices;
    }
}