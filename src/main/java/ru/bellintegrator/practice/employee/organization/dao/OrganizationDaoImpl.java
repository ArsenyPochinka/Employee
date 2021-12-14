package ru.bellintegrator.practice.employee.organization.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.employee.organization.entity.OrganizationEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class OrganizationDaoImpl implements OrganizationDao {

    private final EntityManager em;

    @Autowired
    public OrganizationDaoImpl(EntityManager em) {
        this.em = em;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<OrganizationEntity> list(OrganizationEntity filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<OrganizationEntity> criteriaQuery = criteriaBuilder.createQuery(OrganizationEntity.class);
        Root<OrganizationEntity> organizationRoot = criteriaQuery.from(OrganizationEntity.class);
        Predicate predicate = criteriaBuilder.like(organizationRoot.get("name"), "%" + filter.getName() + "%");
        if (filter.getInn() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(organizationRoot.get("inn"), filter.getInn()));
        }
        if (filter.getIsActive() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(organizationRoot.get("isActive"), filter.getIsActive()));
        }
        criteriaQuery.select(organizationRoot).where(predicate);
        TypedQuery<OrganizationEntity> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public OrganizationEntity getById(Integer id) {
        return em.find(OrganizationEntity.class, id);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(OrganizationEntity updateOrganization) {
        OrganizationEntity organization = getById(updateOrganization.getId());
        organization.setName(updateOrganization.getName());
        organization.setFullName(updateOrganization.getFullName());
        organization.setInn(updateOrganization.getInn());
        organization.setKpp(updateOrganization.getKpp());
        organization.setAddress(updateOrganization.getAddress());
        if(updateOrganization.getPhone() != null) {
            organization.setPhone(updateOrganization.getPhone());
        }
        if(updateOrganization.getIsActive() != null) {
            organization.setIsActive(updateOrganization.getIsActive());
        }
        em.flush();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void save(OrganizationEntity newOrganization) {
        em.persist(newOrganization);
    }
}
