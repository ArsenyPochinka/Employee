package ru.bellintegrator.practice.employee.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.employee.directory.dao.CountryDao;
import ru.bellintegrator.practice.employee.directory.dao.TypeDocDao;
import ru.bellintegrator.practice.employee.user.entity.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

/**
 * {@inheritDoc}
 */
@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager em;
    private final TypeDocDao typeDocDao;
    private final CountryDao countryDao;

    @Autowired
    public UserDaoImpl(EntityManager em, TypeDocDao typeDocDao, CountryDao countryDao) {
        this.em = em;
        this.typeDocDao = typeDocDao;
        this.countryDao = countryDao;
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
            predicate = criteriaBuilder.equal(userRoot.get("doc").get("typeDoc").get("code"), filter.getDoc().getTypeDoc().getCode());
        }
        if (filter.getCountry() != null && filter.getCountry().getCode() != null) {
            predicate = criteriaBuilder.equal(userRoot.get("country").get("code"), filter.getCountry().getCode());
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
        if(updateUser.getOfficeId() != null) {
            user.setOfficeId(updateUser.getOfficeId());
        }
        if(updateUser.getLastName() != null) {
            user.setLastName(updateUser.getLastName());
        }
        if(updateUser.getMiddleName() != null) {
            user.setMiddleName(updateUser.getMiddleName());
        }
        user.setPosition(updateUser.getPosition());
        if(updateUser.getPhone() != null) {
            user.setPhone(updateUser.getPhone());
        }
        if(updateUser.getDoc() != null) {
            if(updateUser.getDoc().getDocNumber() != null) {
                user.getDoc().setDocNumber(updateUser.getDoc().getDocNumber());
            }
            if(updateUser.getDoc().getDocDate() != null) {
                user.getDoc().setDocDate(updateUser.getDoc().getDocDate());
            }
            if(updateUser.getDoc().getTypeDoc() != null) {
                if(updateUser.getDoc().getTypeDoc().getName() != null) {
                    if (Optional.of(typeDocDao.getByName(updateUser.getDoc().getTypeDoc().getName())).isPresent()) {
                        user.getDoc().setTypeDoc(typeDocDao.getByName(updateUser.getDoc().getTypeDoc().getName()));
                    } else {
                        user.getDoc().setTypeDoc(typeDocDao.save(updateUser.getDoc().getTypeDoc()));
                    }
                }
            }
        }
        if(updateUser.getCountry() != null) {
            if(updateUser.getCountry().getCode() != null) {
                if (Optional.of(countryDao.getByCode(updateUser.getCountry().getCode())).isPresent()) {
                    user.setCountry(countryDao.getByCode(updateUser.getCountry().getCode()));
                } else {
                    user.setCountry(countryDao.save(updateUser.getCountry()));
                }
            }
        }
        if(updateUser.getIdentified() != null) {
            user.setIdentified(updateUser.getIdentified());
        }
        em.flush();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void save(UserEntity newUser) {
        if(newUser.getDoc().getTypeDoc() != null) {
            if(newUser.getDoc().getTypeDoc().getName() != null || newUser.getDoc().getTypeDoc().getCode() != null) {
                if (Optional.of(typeDocDao.getByNameAndCode(newUser.getDoc().getTypeDoc().getName(),
                        newUser.getDoc().getTypeDoc().getCode())).isPresent()) {
                    newUser.getDoc().setTypeDoc(typeDocDao.getByNameAndCode(newUser.getDoc().getTypeDoc().getName(),
                            newUser.getDoc().getTypeDoc().getCode()));
                } else {
                    newUser.getDoc().setTypeDoc(typeDocDao.save(newUser.getDoc().getTypeDoc()));
                }
            }
        }
        if(newUser.getCountry() != null) {
        if(newUser.getCountry().getCode() != null) {
            if (Optional.of(countryDao.getByCode(newUser.getCountry().getCode())).isPresent()) {
                newUser.setCountry(countryDao.getByCode(newUser.getCountry().getCode()));
            } else {
                newUser.setCountry(countryDao.save(newUser.getCountry()));
            }
        }
    }
        em.persist(newUser);
    }
}