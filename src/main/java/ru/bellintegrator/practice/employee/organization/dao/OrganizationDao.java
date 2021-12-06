package ru.bellintegrator.practice.employee.organization.dao;

import ru.bellintegrator.practice.employee.organization.entity.Organization;

import java.util.List;

/**
 * DAO for working with Organization
 */
public interface OrganizationDao {
    /**
     * Get all Organizations
     *
     * @return
     */
    List<Organization> all();

    /**
     * Get Organization by id
     *
     * @param id
     * @return
     */
    Organization loadById(Integer id);

    /**
     * Get Organization by name
     *
     * @param name
     * @return
     */
    Organization loadByName(String name);

    /**
     * Update Organization
     *
     * @param id, name, fullName, inn, kpp, address, phone, isActive;
     * @return
     */
    int update(Integer id, String name, String fullName, String inn, String kpp, String address, String phone, boolean isActive);

    /**
     * Save new Organization
     *
     * @param organization
     */
    void save(Organization organization);
}
