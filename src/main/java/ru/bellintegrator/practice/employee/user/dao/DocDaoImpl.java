package ru.bellintegrator.practice.employee.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.employee.user.entity.DocEntity;

import javax.persistence.EntityManager;
/**
 * {@inheritDoc}
 */
@Repository
public class DocDaoImpl implements DocDao {

    private final EntityManager em;

    @Autowired
    public DocDaoImpl(EntityManager em) {
        this.em = em;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public DocEntity save(DocEntity newDoc) {
        em.persist(newDoc);
        return newDoc;
    }
}