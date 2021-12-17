package ru.bellintegrator.practice.employee.office.dao;

import ru.bellintegrator.practice.employee.office.entity.OfficeEntity;

import java.util.List;

/**
 * DAO for working with Office
 */
public interface OfficeDao {
    /**
     * Returns a filtered list of offices
     *
     * @param filter (object with filtering data)
     * @return filtered list of offices
     */
    List<OfficeEntity> list(OfficeEntity filter);

    /**
     * Returns the office with the specified ID
     *
     * @param id (office id)
     * @return office with the specified id
     */
    OfficeEntity getById(Integer id);

    /**
     * Updates information about the office
     *
     * @param updateOffice (object with new office data)
     */
    void update(OfficeEntity updateOffice);

    /**
     * Saves information about the new office
     *
     * @param newOffice (object with data about the new office)
     */
    void save(OfficeEntity newOffice);
}

