package ru.bellintegrator.practice.employee.person.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.employee.directory.dto.CountryDto;
import ru.bellintegrator.practice.employee.directory.entity.CountryEntity;
import ru.bellintegrator.practice.employee.directory.entity.TypeDocEntity;
import ru.bellintegrator.practice.employee.person.dao.PersonDao;
import ru.bellintegrator.practice.employee.person.dto.*;
import ru.bellintegrator.practice.employee.person.entity.DocEntity;
import ru.bellintegrator.practice.employee.person.entity.PersonEntity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
public class PersonServiceImpl implements PersonService {
    private final PersonDao dao;

    @Autowired
    public PersonServiceImpl(PersonDao dao) {
        this.dao = dao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<PersonListResponseDto> getByParams(PersonListRequestDto request) {
        List<PersonEntity> personEntities = dao.loadByParams(request.getOfficeId(), request.getFirstName(), request.getSecondName(), request.getMiddleName(), request.getPost(), request.getDocCode(), request.getCitizenshipCodes());
        return personEntities.stream().map(pe -> new PersonListResponseDto(pe.getId(), pe.getFirstName(), pe.getSecondName(), pe.getMiddleName(), pe.getPost())).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public PersonDto getById(Integer id) {
        PersonEntity personEntity = dao.loadById(id);
        CountryDto[] countries = (CountryDto[]) personEntity.getCountries().stream().map(ce -> new CountryDto(ce.getName(), ce.getCode())).toArray();
        return new PersonDto(personEntity.getId(), personEntity.getFirstName(), personEntity.getSecondName(), personEntity.getMiddleName(), personEntity.getPost(), personEntity.getPhone(), personEntity.getDoc().getTypeDoc().getName(), personEntity.getDoc().getDocNumber(), personEntity.getDoc().getDocDate(), countries , personEntity.getIsIdentified());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(PersonUpdateDto personUpdateDto) {
        Set<CountryEntity> countries = null;
        if(personUpdateDto.getCitizenshipCodes() != null) {
            countries = Arrays.stream(personUpdateDto.getCitizenshipCodes()).map(code -> new CountryEntity(code)).collect(Collectors.toSet());
        }
        PersonEntity newPersonEntity = new PersonEntity(personUpdateDto.getFirstName(), personUpdateDto.getSecondName(), personUpdateDto.getMiddleName(), personUpdateDto.getPost(), personUpdateDto.getPhone(), personUpdateDto.getIsIdentified(), new DocEntity(personUpdateDto.getDocNumber(), personUpdateDto.getDocDate(), new TypeDocEntity(personUpdateDto.getDocName())), countries);
        newPersonEntity.setId(personUpdateDto.getId());
        dao.update(newPersonEntity);
    }

    @Override
    @Transactional
    public void add(PersonSaveDto personSaveDto) {
        Set<CountryEntity> countries = null;
        if(personSaveDto.getCitizenshipCodes() != null) {
            countries = Arrays.stream(personSaveDto.getCitizenshipCodes()).map(code -> new CountryEntity(code)).collect(Collectors.toSet());
        }
                PersonEntity newPersonEntity = new PersonEntity(personSaveDto.getOfficeId(), personSaveDto.getFirstName(), personSaveDto.getSecondName(), personSaveDto.getMiddleName(), personSaveDto.getPost(), personSaveDto.getPhone(), personSaveDto.getIsIdentified(), new DocEntity(personSaveDto.getDocNumber(), personSaveDto.getDocDate(), new TypeDocEntity(personSaveDto.getDocName(), personSaveDto.getDocCode())), countries);
        dao.save(newPersonEntity);
    }
}
