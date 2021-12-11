package ru.bellintegrator.practice.employee.directory.dao;

import ru.bellintegrator.practice.employee.directory.entity.CountryEntity;

import java.util.List;

/**
 * DAO for working with Guides of country
 */
public interface CountryDao {
    /**
     * Get all Countries
     *
     * @return List<CountryEntity>
     */
    List<CountryEntity> all();
        /**
     * Get Guides of country by specific attributes (name, code)
     *
     * @param name, code
     * @return List<CountryEntity>
     */
     List<CountryEntity> loadByParams(String name, String code);
    /**
     * Get Guides of country by specific attributes (name, code)
     *
     * @param code
     * @return List<CountryEntity>
     */
    List<CountryEntity> loadByCode(String code);
    /**
     * Save new Country
     *
     * @param countryEntity
     */
    void save(CountryEntity countryEntity);
}
