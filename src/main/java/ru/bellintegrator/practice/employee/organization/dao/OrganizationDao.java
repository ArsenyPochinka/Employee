package ru.bellintegrator.practice.employee.organization.dao;

import ru.bellintegrator.practice.employee.organization.entity.OrganizationEntity;

import java.util.List;

/**
 * DAO for working with Organization
 */
public interface OrganizationDao {
    /**
     * Returns a filtered list of organizations
     *
     * @param filter (object with filtering data)
     * @return filtered list of organizations
     */
    List<OrganizationEntity> list(OrganizationEntity filter);

    /**
     * Returns the organization with the specified ID
     *
     * @param id (organization id)
     * @return organization with the specified id
     */
    OrganizationEntity getById(Integer id);

    /**
     * Updates information about the organization
     *
     * @param updateOrganization (object with new organization data)
     */
    void update(OrganizationEntity updateOrganization);

    /**
     * Saves information about the new organization
     *
     * @param newOrganization (object with data about the new organization)
     */
    void save(OrganizationEntity newOrganization);
}
