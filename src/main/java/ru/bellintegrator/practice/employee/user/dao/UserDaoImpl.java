package ru.bellintegrator.practice.employee.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.employee.user.entity.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager em;

    @Autowired
    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserEntity> list(UserEntity filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
        Root<UserEntity> userRoot = criteriaQuery.from(UserEntity.class);
        Predicate predicate = criteriaBuilder.equal(userRoot.get("officeId"), filter.getOfficeId());
        if (filter.getFirstName() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(userRoot.get("firstName"), filter.getFirstName()));
        }
        if (filter.getLastName() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(userRoot.get("lastName"), filter.getLastName()));
        }
        if (filter.getMiddleName() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(userRoot.get("middleName"), filter.getMiddleName()));
        }
        if (filter.getPosition() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(userRoot.get("position"), filter.getPosition()));
        }
        if (filter.getDoc() != null && filter.getDoc().getTypeDoc().getCode() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(userRoot.get("doc").get("typeDoc").get("code"), filter.getDoc().getTypeDoc().getCode()));
        }
        if (filter.getCountry() != null && filter.getCountry().getCode() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(userRoot.get("country").get("code"), filter.getCountry().getCode()));
        }
        criteriaQuery.select(userRoot).where(predicate);
        TypedQuery<UserEntity> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public UserEntity getById(Integer id) {
        return em.find(UserEntity.class, id);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(UserEntity updateUser) {
        UserEntity user = getById(updateUser.getId());
        user.setFirstName(updateUser.getFirstName());
        user.setPosition(updateUser.getPosition());
        if(updateUser.getOfficeId() != null) {
            user.setOfficeId(updateUser.getOfficeId());
        }
        if(updateUser.getLastName() != null) {
            user.setLastName(updateUser.getLastName());
        }
        if(updateUser.getMiddleName() != null) {
            user.setMiddleName(updateUser.getMiddleName());
        }
        if(updateUser.getPhone() != null) {
            user.setPhone(updateUser.getPhone());
        }
        if(updateUser.getCountry() != null) {
            user.setCountry(updateUser.getCountry());
        }
        if(updateUser.getIsIdentified() != null) {
            user.setIsIdentified(updateUser.getIsIdentified());
        }
        if(updateUser.getDoc() != null) {
            user.setDoc(updateUser.getDoc());
        }
        em.flush();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void save(UserEntity newUser) {
        em.persist(newUser);
    }
}