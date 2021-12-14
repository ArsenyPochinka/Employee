package ru.bellintegrator.practice.employee.directory.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.employee.directory.entity.TypeDocEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
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
    public List<TypeDocEntity> list() {
        TypedQuery<TypeDocEntity> query = em.createQuery("SELECT t FROM Type_Doc t", TypeDocEntity.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypeDocEntity getByName(String name) {
        TypedQuery<TypeDocEntity> query = em.createQuery("SELECT t FROM Type_Doc t WHERE t.name = :name", TypeDocEntity.class);
        query.setParameter("name", name);
        TypeDocEntity typeDoc;
        try {
            typeDoc = query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return typeDoc;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public TypeDocEntity getByNameAndCode(String name, String code) {
        TypedQuery<TypeDocEntity> query = em.createQuery("SELECT t FROM Type_Doc t WHERE t.code = :code AND t.name = :name", TypeDocEntity.class);
        query.setParameter("name", name);
        query.setParameter("code", code);
        TypeDocEntity typeDoc;
        try {
            typeDoc = query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return typeDoc;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public TypeDocEntity save(TypeDocEntity newTypeDoc) {
        em.persist(newTypeDoc);
        return newTypeDoc;
    }
}
