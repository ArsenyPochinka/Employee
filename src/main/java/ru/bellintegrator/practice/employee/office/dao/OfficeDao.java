package ru.bellintegrator.practice.employee.office.dao;

import ru.bellintegrator.practice.employee.office.entity.OfficeEntity;

import java.util.List;

/**
 * DAO for working with Office
 */
public interface OfficeDao {
    /**
     * Get all Offices
     *
     * @return List<OfficeEntity>
     */
    List<OfficeEntity> all();

    /**
     * Get Office by id
     *
     * @param id
     * @return OfficeEntity
     */
    OfficeEntity loadById(Integer id);

    /**
     * Get Offices with specific attributes (organizationId, name, phone, isActive)
     *
     * @param organizationId, name, phone, isActive
     * @return List<OfficeEntity>
     */
    List<OfficeEntity> loadByParams(Integer organizationId, String name, String phone, Boolean isActive);

    /**
     * Update Office
     *
     * @param officeEntity ;
     */
    void update(OfficeEntity officeEntity);

    /**
     * Save new Office
     *
     * @param officeEntity
     */
    void save(OfficeEntity officeEntity);
}
