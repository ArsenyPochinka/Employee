package ru.bellintegrator.practice.employee.directory.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.employee.directory.entity.CountryEntity;
import ru.bellintegrator.practice.employee.directory.entity.TypeDocEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
/**
 * {@inheritDoc}
 */
@Repository
public class TypeDocDaoImpl implements TypeDocDao {

    private final EntityManager em;

    @Autowired
    public TypeDocDaoImpl(EntityManager em) {
        this.em = em;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<TypeDocEntity> loadByParams(String name, String code) {
        CriteriaQuery<TypeDocEntity> criteria = buildCriteriaQuery(name, code);
        TypedQuery<TypeDocEntity> query = em.createQuery(criteria);
        return query.getResultList();
    }

    private CriteriaQuery<TypeDocEntity> buildCriteriaQuery(String name, String code) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<TypeDocEntity> criteriaQuery = builder.createQuery(TypeDocEntity.class);

        Root<TypeDocEntity> root = criteriaQuery.from(TypeDocEntity.class);

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
    /**
     * {@inheritDoc}
     */
    @Override
    public void save(TypeDocEntity typeDocEntity) {
        em.persist(typeDocEntity);
    }
}
