package ru.bellintegrator.practice.employee.person.dao;

import ru.bellintegrator.practice.employee.organization.entity.OrganizationEntity;
import ru.bellintegrator.practice.employee.person.entity.PersonEntity;

import java.util.List;

/**
 * DAO for working with Organization
 */
public interface PersonDao {

    /**
     * Get Person by id
     *
     * @param id
     * @return PersonEntity
     */
    PersonEntity loadById(Integer id);

    /**
     * Get Persons by params (officeId, firstName, lastName, middleName, docCode, citizenshipCode)
     *
     * @param officeId, firstName, lastName, middleName, docCode, citizenshipCodes
     * @return List<PersonEntity>
     */
    List<PersonEntity> loadByParams(Integer officeId, String firstName, String secondName, String middleName, String post, String docCode, String[] citizenshipCodes);

    /**
     * Update Person
     *
     * @param personEntity ;
     */
    void update(PersonEntity personEntity);

    /**
     * Save new Person
     *
     * @param personEntity
     */
    void save(PersonEntity personEntity);
}
