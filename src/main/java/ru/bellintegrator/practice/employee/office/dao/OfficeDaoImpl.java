package ru.bellintegrator.practice.employee.office.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.employee.office.entity.OfficeEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class OfficeDaoImpl implements OfficeDao {

    private final EntityManager em;

    @Autowired
    public OfficeDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OfficeEntity> list(OfficeEntity filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<OfficeEntity> criteriaQuery = criteriaBuilder.createQuery(OfficeEntity.class);
        Root<OfficeEntity> organizationRoot = criteriaQuery.from(OfficeEntity.class);
        Predicate predicate = criteriaBuilder.equal(organizationRoot.get("orgId"), filter.getOrgId());
        if (filter.getName() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(organizationRoot.get("name"), filter.getName()));
        }
        if (filter.getPhone() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(organizationRoot.get("phone"), filter.getPhone()));
        }
        if (filter.getIsActive()!= null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(organizationRoot.get("isActive"), filter.getIsActive()));
        }
        criteriaQuery.select(organizationRoot).where(predicate);
        TypedQuery<OfficeEntity> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public OfficeEntity getById(Integer id) {
        return em.find(OfficeEntity.class, id);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(OfficeEntity updateOffice) {
        OfficeEntity office = getById(updateOffice.getId());
        office.setName(updateOffice.getName());
        office.setAddress(updateOffice.getAddress());
        if(updateOffice.getPhone() != null) {
            office.setPhone(updateOffice.getPhone());
        }
        if(updateOffice.getIsActive() != null) {
            office.setIsActive(updateOffice.getIsActive());
        }
        em.flush();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void save(OfficeEntity newOffice) {
        em.persist(newOffice);
    }
}