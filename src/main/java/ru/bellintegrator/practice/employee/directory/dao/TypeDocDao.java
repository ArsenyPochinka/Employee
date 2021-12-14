package ru.bellintegrator.practice.employee.directory.dao;

import ru.bellintegrator.practice.employee.directory.entity.TypeDocEntity;

import java.util.List;

/**
 * DAO for working with Guides of doc's type
 */
public interface TypeDocDao {
    /**
     * Returns a list of doc's type
     *
     * @return list of doc's type
     */
    List<TypeDocEntity> list();

    /**
     * Returns the doc's type with the specified name
     *
     * @param name name of doc's type
     * @return doc's type with the specified name
     */
    TypeDocEntity getByName(String name);
    /**
     * Returns the doc's type with the specified code
     *
     * @param name name of doc's type
     * @param code code of doc's type
     * @return doc's type with the specified name and code
     */
    TypeDocEntity getByNameAndCode(String name, String code);
    /**
     * Save new TypeDoc
     *
     * @param newTypeDoc (object with data about the new type of doc)
     * @return new doc's type
     */
    TypeDocEntity save(TypeDocEntity newTypeDoc);
}
