package ru.bellintegrator.practice.employee.organization.dao;

import ru.bellintegrator.practice.employee.organization.entity.OrganizationEntity;

import java.util.List;

/**
 * DAO for working with Organization
 */
public interface OrganizationDao {
    /**
     * Get all Organizations
     *
     * @return List<OrganizationEntity>
     */
    List<OrganizationEntity> all();

    /**
     * Get Organization by id
     *
     * @param id
     * @return OrganizationEntity
     */
    OrganizationEntity loadById(Integer id);

    /**
     * Get Organizations by params (name, inn, isActive)
     *
     * @param name, inn, isActive
     * @return List<OrganizationEntity>
     */
    List<OrganizationEntity> loadByParams(String name, String inn, Boolean isActive);

    /**
     * Update Organization
     *
     * @param organizationEntity ;
     */
    void update(OrganizationEntity organizationEntity);

    /**
     * Save new Organization
     *
     * @param organizationEntity
     */
    void save(OrganizationEntity organizationEntity);
}
