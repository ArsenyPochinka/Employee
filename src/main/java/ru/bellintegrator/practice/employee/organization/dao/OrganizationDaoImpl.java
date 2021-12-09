package ru.bellintegrator.practice.employee.organization.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.employee.organization.entity.OrganizationEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
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
    public List<OrganizationEntity> all() {
        TypedQuery<OrganizationEntity> query = em.createQuery("SELECT o FROM Organization o", OrganizationEntity.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrganizationEntity loadById(Integer id) {
        return em.find(OrganizationEntity.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OrganizationEntity> loadByParams(String name, String inn, Boolean isActive) {
        CriteriaQuery<OrganizationEntity> criteria = buildCriteriaQuery(name, inn, isActive);
        TypedQuery<OrganizationEntity> query = em.createQuery(criteria);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(OrganizationEntity organizationEntity) {
        em.persist(organizationEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(OrganizationEntity organizationEntity) {
        em.createQuery(buildCriteriaUpdate(organizationEntity)).executeUpdate();
    }

    private CriteriaQuery<OrganizationEntity> buildCriteriaQuery(String name, String inn, Boolean isActive) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<OrganizationEntity> criteriaQuery = builder.createQuery(OrganizationEntity.class);

        Root<OrganizationEntity> root = criteriaQuery.from(OrganizationEntity.class);

        if(inn != null && isActive != null) {
            criteriaQuery.where(root.get("name").in(name), root.get("inn").in(inn), root.get("isActive").in(isActive));
        }
        if(inn != null && isActive == null) {
            criteriaQuery.where(root.get("name").in(name), root.get("inn").in(inn));
        }
        if(inn == null && isActive != null) {
            criteriaQuery.where(root.get("name").in(name), root.get("isActive").in(isActive));
        }
        if(inn == null && isActive == null) {
            criteriaQuery.where(root.get("name").in(name));
        }

        return criteriaQuery;
    }

    private CriteriaUpdate<OrganizationEntity> buildCriteriaUpdate(OrganizationEntity organizationEntity) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaUpdate<OrganizationEntity> criteriaUpdate = builder.createCriteriaUpdate(OrganizationEntity.class);
        Root<OrganizationEntity> root = criteriaUpdate.from(OrganizationEntity.class);
        criteriaUpdate.set("name", organizationEntity.getName());
        criteriaUpdate.set("fullName", organizationEntity.getFullName());
        criteriaUpdate.set("inn", organizationEntity.getInn());
        criteriaUpdate.set("kpp", organizationEntity.getKpp());
        criteriaUpdate.set("address", organizationEntity.getAddress());
        if(organizationEntity.getPhone() != null) {
            criteriaUpdate.set("phone", organizationEntity.getPhone());
        }
        if(organizationEntity.getIsActive() != null) {
            criteriaUpdate.set("isActive", organizationEntity.getIsActive());
        }
        criteriaUpdate.where(builder.equal(root.get("id"), organizationEntity.getId()));

        return criteriaUpdate;
    }


}
