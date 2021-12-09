package ru.bellintegrator.practice.employee.office.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.employee.office.entity.OfficeEntity;

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
    public List<OfficeEntity> all() {
        TypedQuery<OfficeEntity> query = em.createQuery("SELECT o FROM Office o", OfficeEntity.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OfficeEntity loadById(Integer id) {
        return em.find(OfficeEntity.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OfficeEntity> loadByParams(Integer organizationId, String name, String phone, Boolean isActive) {
        CriteriaQuery<OfficeEntity> criteria = buildCriteriaQuery(organizationId, name, phone, isActive);
        TypedQuery<OfficeEntity> query = em.createQuery(criteria);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(OfficeEntity officeEntity) {
        em.persist(officeEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(OfficeEntity officeEntity) {
        em.createQuery(buildCriteriaUpdate(officeEntity)).executeUpdate();
    }

    private CriteriaQuery<OfficeEntity> buildCriteriaQuery(Integer organizationId, String name, String phone, Boolean isActive) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<OfficeEntity> criteriaQuery = builder.createQuery(OfficeEntity.class);

        Root<OfficeEntity> root = criteriaQuery.from(OfficeEntity.class);

        if(name != null && phone != null && isActive != null) {
            criteriaQuery.where(root.get("organizationId").in(organizationId), root.get("name").in(name), root.get("phone").in(phone), root.get("isActive").in(isActive));
        }
        if(name != null && phone != null && isActive == null) {
            criteriaQuery.where(root.get("organizationId").in(organizationId), root.get("name").in(name), root.get("phone").in(phone));
        }
        if(name != null && phone == null && isActive != null) {
            criteriaQuery.where(root.get("organizationId").in(organizationId), root.get("name").in(name), root.get("isActive").in(isActive));
        }
        if( name != null && phone == null && isActive == null) {
            criteriaQuery.where(root.get("organizationId").in(organizationId), root.get("name").in(name));
        }
        if(name == null && phone != null && isActive != null) {
            criteriaQuery.where(root.get("organizationId").in(organizationId), root.get("phone").in(phone), root.get("isActive").in(isActive));
        }
        if(name == null && phone != null && isActive == null) {
            criteriaQuery.where(root.get("organizationId").in(organizationId), root.get("phone").in(phone));
        }
        if(name == null && phone == null && isActive != null) {
            criteriaQuery.where(root.get("organizationId").in(organizationId), root.get("isActive").in(isActive));
        }
        if( name == null && phone == null && isActive == null) {
            criteriaQuery.where(root.get("organizationId").in(organizationId));
        }

        return criteriaQuery;
    }

    private CriteriaUpdate<OfficeEntity> buildCriteriaUpdate(OfficeEntity officeEntity) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaUpdate<OfficeEntity> criteriaUpdate = builder.createCriteriaUpdate(OfficeEntity.class);
        Root<OfficeEntity> root = criteriaUpdate.from(OfficeEntity.class);
        criteriaUpdate.set("name", officeEntity.getName());
        criteriaUpdate.set("address", officeEntity.getAddress());
        if(officeEntity.getPhone() != null) {
            criteriaUpdate.set("phone", officeEntity.getPhone());
        }
        if(officeEntity.getIsActive() != null) {
            criteriaUpdate.set("isActive", officeEntity.getIsActive());
        }
        criteriaUpdate.where(builder.equal(root.get("id"), officeEntity.getId()));

        return criteriaUpdate;
    }
}
