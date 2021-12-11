package ru.bellintegrator.practice.employee.directory.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.employee.directory.entity.CountryEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

    @Override
    public List<CountryEntity> all() {
        Query query = em.createNativeQuery("SELECT c.* FROM Country c", CountryEntity.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CountryEntity> loadByParams(String name, String code) {
        CriteriaQuery<CountryEntity> criteria = buildCriteriaQuery(name, code);
        TypedQuery<CountryEntity> query = em.createQuery(criteria);
        return query.getResultList();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<CountryEntity> loadByCode(String code) {
        CriteriaQuery<CountryEntity> criteria = buildCriteriaQuery(code);
        TypedQuery<CountryEntity> query = em.createQuery(criteria);
        return query.getResultList();
    }

    private CriteriaQuery<CountryEntity> buildCriteriaQuery(String name, String code) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<CountryEntity> criteriaQuery = builder.createQuery(CountryEntity.class);

        Root<CountryEntity> root = criteriaQuery.from(CountryEntity.class);

        if(name != null && code != null) {
            criteriaQuery.where(root.get("name").in(name), root.get("code").in(code));
        }
        if(name != null && code == null) {
            criteriaQuery.where(root.get("name").in(name));
        }
        if(name == null && code != null) {
            criteriaQuery.where(root.get("code").in(code));
        }
        return criteriaQuery;
    }

    private CriteriaQuery<CountryEntity> buildCriteriaQuery(String code) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<CountryEntity> criteriaQuery = builder.createQuery(CountryEntity.class);

        Root<CountryEntity> root = criteriaQuery.from(CountryEntity.class);

        criteriaQuery.where(root.get("code").in(code));
        return criteriaQuery;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void save(CountryEntity countryEntity) {
        em.persist(countryEntity);
    }
}
