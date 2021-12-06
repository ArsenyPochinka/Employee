package ru.bellintegrator.practice.employee.organization.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.employee.organization.entity.Organization;

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
    public List<Organization> all() {
        TypedQuery<Organization> query = em.createQuery("SELECT o FROM Organization o", Organization.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization loadById(Integer id) {
        return em.find(Organization.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization loadByName(String name) {
        CriteriaQuery<Organization> criteria = buildCriteria(name);
        TypedQuery<Organization> query = em.createQuery(criteria);
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Organization organization) {
        em.persist(organization);
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public int update(Integer id, String name, String fullName, String inn, String kpp, String address, String phone, boolean isActive) {
        Query query = em.createQuery("UPDATE Organization o SET o.name = :nameParam, " +
                "o.fullName = :fullNameParam, o.inn = :innParam, o.kpp = :kppParam, " +
                "o.address = :addressParam, o.phone = :phoneParam, o.isActive = :isActiveParam" +
                " WHERE o.id = :idParam ");
        query.setParameter("nameParam", name);
        query.setParameter("fullNameParam", fullName);
        query.setParameter("innParam", inn);
        query.setParameter("kppParam", kpp);
        query.setParameter("addressParam", address);
        query.setParameter("phoneParam", phone);
        query.setParameter("isActiveParam", isActive);
        query.setParameter("idParam", id);
        return query.executeUpdate();

    }

    private CriteriaQuery<Organization> buildCriteria(String name) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteria = builder.createQuery(Organization.class);

        Root<Organization> organization = criteria.from(Organization.class);
        criteria.where(builder.equal(organization.get("name"), name));

        return criteria;
    }
}
