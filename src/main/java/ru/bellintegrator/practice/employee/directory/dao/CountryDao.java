package ru.bellintegrator.practice.employee.directory.dao;

import ru.bellintegrator.practice.employee.directory.entity.CountryEntity;
import ru.bellintegrator.practice.employee.directory.entity.TypeDocEntity;

import java.util.List;

/**
 * DAO for working with Guides of country
 */
public interface CountryDao {
    /**
     * Returns a list of countries and their codes
     *
     * @return list of countries and their codes
     */
    List<CountryEntity> list();

    /**
     * Returns the country with the specified code
     *
     * @param code code of country
     * @return country with the specified code
     */
    CountryEntity getByCode(String code);
    /**
     * Save new Country
     *
     * @param newCountry (object with data about the new country)
     * @return new country
     */
    CountryEntity save(CountryEntity newCountry);
}
