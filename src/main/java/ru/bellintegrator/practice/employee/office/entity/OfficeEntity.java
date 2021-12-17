package ru.bellintegrator.practice.employee.office.entity;


import ru.bellintegrator.practice.employee.user.entity.UserEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Office Entity
 */
@Entity(name = "Office")
public class OfficeEntity {
    /**
     * Unique office identifier
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
     * Unique organization identifier
     */
    @Column(name = "org_id", nullable = false)
    private Integer orgId;

    /**
     * name of office
     */
    @Column(name = "name", length = 50)
    private String name;

    /**
     * address of office
     */
    @Column(name = "address", length = 50)
    private String address;

    /**
     * phone of office
     */
    @Column(name = "phone", length = 16)
    private String phone;

    /**
     * Is the organization in operation (is_active = true / false)
     */
    @Column(name = "is_active")
    private Boolean isActive;
    /**
     * List of office's users
     */
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "office_id")
    private List<UserEntity> users;

    /**
     * Constructor for Hibernate
     */
    public OfficeEntity() {

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

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}