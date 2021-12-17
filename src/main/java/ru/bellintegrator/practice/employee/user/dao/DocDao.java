package ru.bellintegrator.practice.employee.user.dao;

import ru.bellintegrator.practice.employee.user.entity.DocEntity;

/**
 * DAO for working with Doc
 */
public interface DocDao {
    /**
     * Saves information about the new doc
     *
     * @param newDoc (object with data about the new doc)
     */
    DocEntity save(DocEntity newDoc);
}

