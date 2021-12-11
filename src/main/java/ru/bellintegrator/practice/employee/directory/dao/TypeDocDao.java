package ru.bellintegrator.practice.employee.directory.dao;

import ru.bellintegrator.practice.employee.directory.entity.CountryEntity;
import ru.bellintegrator.practice.employee.directory.entity.TypeDocEntity;

import java.util.List;

/**
 * DAO for working with Guides of country
 */
public interface TypeDocDao {
    /**
     * Get Guides of TypeDoc by specific attributes (name, code)
     *
     * @param name, code
     * @return List<TypeDocEntity>
     */
    List<TypeDocEntity> loadByParams(String name, String code);
    /**
     * Save new TypeDoc
     *
     * @param typeDocEntity
     */
    void save(TypeDocEntity typeDocEntity);
}
