package ru.bellintegrator.practice.employee.person.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.employee.directory.dao.CountryDao;
import ru.bellintegrator.practice.employee.directory.entity.CountryEntity;
import ru.bellintegrator.practice.employee.directory.entity.TypeDocEntity;
import ru.bellintegrator.practice.employee.person.entity.DocEntity;
import ru.bellintegrator.practice.employee.person.entity.PersonEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * {@inheritDoc}
 */
@Repository
public class PersonDaoImpl implements PersonDao {

    private final EntityManager em;
    private final CountryDao countryDao;

    @Autowired
    public PersonDaoImpl(EntityManager em, CountryDao countryDao) {
        this.em = em;
        this.countryDao = countryDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonEntity loadById(Integer id) {
        TypedQuery<PersonEntity> namedQuery = em.createNamedQuery("Person.getById", PersonEntity.class);
        namedQuery.setParameter("id", id);
        return namedQuery.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PersonEntity> loadByParams(Integer officeId, String firstName, String secondName, String middleName, String post, String docCode, String[] citizenshipCodes) {
        CriteriaQuery<PersonEntity> criteria = buildCriteriaQuery(officeId, firstName, secondName, middleName, post, docCode, citizenshipCodes);
        TypedQuery<PersonEntity> query = em.createQuery(criteria);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(PersonEntity personEntity) {
        em.persist(personEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(PersonEntity personEntity) {
        em.createQuery(buildCriteriaUpdate(personEntity)).executeUpdate();
    }

    private CriteriaQuery<PersonEntity> buildCriteriaQuery(Integer officeId, String firstName, String secondName, String middleName, String post, String docCode, String[] citizenshipCodes) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<PersonEntity> criteriaQuery = builder.createQuery(PersonEntity.class);
        CriteriaQuery<DocEntity> criteriaQueryDoc = builder.createQuery(DocEntity.class);
        CriteriaQuery<TypeDocEntity> criteriaQueryTypeDoc = builder.createQuery(TypeDocEntity.class);
        CriteriaQuery<CountryEntity> criteriaQueryCountry = builder.createQuery(CountryEntity.class);

        Root<PersonEntity> root = criteriaQuery.from(PersonEntity.class);
        Root<DocEntity> rootDoc = criteriaQueryDoc.from(DocEntity.class);
        Root<TypeDocEntity> rootTypeDoc = criteriaQueryTypeDoc.from(TypeDocEntity.class);

        criteriaQuery = criteriaQuery.where(root.get("officeId").in(officeId));

        if(firstName !=null) {
            criteriaQuery = criteriaQuery.where(root.get("firstName").in(firstName));
        }
        if(secondName !=null) {
            criteriaQuery = criteriaQuery.where(root.get("secondName").in(secondName));
        }
        if(middleName !=null) {
            criteriaQuery = criteriaQuery.where(root.get("middleName").in(middleName));
        }
        if(post !=null) {
            criteriaQuery = criteriaQuery.where(root.get("post").in(post));
        }
        if(docCode !=null) {
            criteriaQuery = criteriaQuery.where(builder.equal(root.get("doc"),
                    criteriaQueryDoc.where(builder.equal(rootDoc.get("typeDoc"),
                            criteriaQueryTypeDoc.where(rootTypeDoc.get("docCode").in(docCode))))));
        }
        if(citizenshipCodes !=null) {
            List<String> listCodes = Arrays.asList(citizenshipCodes);
            em.createQuery(criteriaQuery).getSingleResult().getCountries().forEach(country -> Optional.of(listCodes.stream().filter(code -> code.equals(country.getCode()))).ifPresent(listCodes::remove));
            if (!listCodes.isEmpty()) {
                // always an incorrect statement "equal(root.get("id"), null)"
                criteriaQuery = criteriaQuery.where(builder.equal(root.get("id"), null));
            }
        }
        return criteriaQuery;
    }

    private CriteriaUpdate<PersonEntity> buildCriteriaUpdate(PersonEntity changePerson) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaUpdate<PersonEntity> criteriaUpdate = builder.createCriteriaUpdate(PersonEntity.class);
        Root<PersonEntity> root = criteriaUpdate.from(PersonEntity.class);
        if(changePerson.getOfficeId() != null) {
            criteriaUpdate.set("officeId", changePerson.getOfficeId());
        }
        criteriaUpdate.set("firstName", changePerson.getFirstName());
        if(changePerson.getSecondName() != null) {
            criteriaUpdate.set("secondName", changePerson.getSecondName());
        }
        if(changePerson.getMiddleName() != null) {
            criteriaUpdate.set("middleName", changePerson.getMiddleName());
        }
        criteriaUpdate.set("post", changePerson.getPost());
        if(changePerson.getPhone() != null) {
            criteriaUpdate.set("phone", changePerson.getPhone());
        }
        if(changePerson.getDoc() != null) {
            if(em.find(PersonEntity.class, changePerson.getId()).getDoc().getTypeDoc().getName().equals(changePerson.getDoc().getTypeDoc().getName())) {
                em.find(PersonEntity.class, changePerson.getId()).getDoc().setDocNumber(changePerson.getDoc().getDocNumber());
                em.find(PersonEntity.class, changePerson.getId()).getDoc().setDocDate(changePerson.getDoc().getDocDate());
            } else {
                em.find(PersonEntity.class, changePerson.getId()).setDoc(new DocEntity(changePerson.getDoc().getDocNumber(), changePerson.getDoc().getDocDate(), new TypeDocEntity(changePerson.getDoc().getTypeDoc().getName())));
            }
        }
        if(changePerson.getCountries() != null) {
            changePerson.getCountries().forEach(country -> {
                if(countryDao.loadByCode(country.getCode()).isEmpty()) {
               //     countryDao.save(new CountryEntity(country.getName(),country.getCode()));
                    em.find(PersonEntity.class, changePerson.getId()).getCountries().add(new CountryEntity(country.getName(),country.getCode()));
                }
            });
        }
        if(changePerson.getIsIdentified() != null) {
            criteriaUpdate.set("phone", changePerson.getIsIdentified());
        }
        criteriaUpdate.where(builder.equal(root.get("id"), changePerson.getId()));

        return criteriaUpdate;
    }



}
