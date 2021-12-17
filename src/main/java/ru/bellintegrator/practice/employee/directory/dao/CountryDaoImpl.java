package ru.bellintegrator.practice.employee.directory.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.employee.directory.entity.CountryEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class CountryDaoImpl implements CountryDao {

    private final EntityManager em;

    @Autowired
    public CountryDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CountryEntity> list() {
        TypedQuery<CountryEntity> query = em.createQuery("SELECT c FROM Country c", CountryEntity.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CountryEntity getByCode(String code) {
        TypedQuery<CountryEntity> query = em.createQuery("SELECT c FROM Country c WHERE c.code = :code", CountryEntity.class);
        query.setParameter("code", code);
        CountryEntity country;
        try {
            country = query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return country;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void save(CountryEntity newCountry) {
        em.persist(newCountry);
    }
}
